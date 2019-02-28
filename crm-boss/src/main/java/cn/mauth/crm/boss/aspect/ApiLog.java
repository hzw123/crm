package cn.mauth.crm.boss.aspect;

import cn.mauth.crm.util.common.HttpUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
@Order(1)
public class ApiLog {

    private static final Logger log= LoggerFactory.getLogger(ApiLog.class);

    private ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    @Pointcut("execution(public * cn.mauth.crm.boss.controller.api.*.*(..))")
    public void log(){}


    @Before("log()")
    public void before(JoinPoint joinPoint){
        threadLocal.set(System.currentTimeMillis());

        HttpServletRequest request= HttpUtil.getRequest();

        String uri=request.getRequestURI();

        String type=request.getMethod();

        String method=joinPoint.getSignature().getName();

        String className=joinPoint.getSignature().getDeclaringTypeName();

        Object[] objects=joinPoint.getArgs();

        String param= Arrays.toString(objects);

        log.info("\n{\n\turi:{},\n\ttype:{},\n\tclassName:{}," +
                        "\n\tmethod:{},\n\tparam:{},\n}",
                uri,type,className,method, param);
    }


    @AfterReturning("log()")
    public void after(JoinPoint joinPoint){
        long time=System.currentTimeMillis()-threadLocal.get();

        log.info("用时:{}",time);

    }

    @Before("@annotation(api)")
    public void api(JoinPoint joinPoint,ApiOperation api){
        log.info("api的作用:{}",api.value());
    }


}

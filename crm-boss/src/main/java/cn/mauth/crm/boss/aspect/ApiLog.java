package cn.mauth.crm.boss.aspect;

import cn.mauth.crm.util.common.HttpUtil;
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
        log.warn("into aspect of Log ");
    }


    @AfterReturning("log()")
    public void after(JoinPoint joinPoint){
        this.log(joinPoint);
    }

    private void log(JoinPoint joinPoint){
        HttpServletRequest request= HttpUtil.getRequest();

        String uri=request.getRequestURI();
        String type=request.getMethod();

        String method=joinPoint.getSignature().getName();

        String className=joinPoint.getSignature().getDeclaringTypeName();

        Object[] objects=joinPoint.getArgs();

        String param= Arrays.toString(objects);

        long time=System.currentTimeMillis()-threadLocal.get();

        log.info("\n{\n\turi:{},\n\ttype:{},\n\tclassName:{}," +
                        "\n\tmethod:{},\n\tparam:{},\n\ttimeLong:{}\n}",
                uri,type,className,method, param,time);
    }
}

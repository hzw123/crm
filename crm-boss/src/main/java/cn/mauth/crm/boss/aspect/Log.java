package cn.mauth.crm.boss.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class Log {

    private static final Logger log= LoggerFactory.getLogger(Log.class);

    @Pointcut("execution(public * cn.mauth.crm.boss.controller.*.*(..))")
    public void log(){}


    @Before("log()")
    public void before(JoinPoint joinPoint){
        log.warn("into aspect of Log ");
    }
}

package com.hm.common.utils;

import org.aopalliance.intercept.Joinpoint;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;


/**
 * Created by hmaccelerate on 2016/4/9.
 * Description:日志切面工具类
 */
@Component
@Aspect
public class LogAopUtil {

    static Logger log = Logger.getLogger(LogAopUtil.class.getName());

    public void beforeAdvice() {
        System.out.println("Going to setup student profile.");
    }

    /**
     * This is the method which I would like to execute
     * after a selected method execution.
     */
    @After(value = "execution(* com.hm.cms.service.imp.ArticleServiceImpl.*(..))")
    public void afterAdvice() {
        log.debug("this is test");
    }

    /**
     * This is the method which I would like to execute
     * when any method returns.
     */
    public void afterReturningAdvice(Object retVal) {
        System.out.println("Returning:" + retVal.toString());
    }

    /**
     * This is the method which I would like to execute
     * if there is an exception raised.
     */
    public void AfterThrowingAdvice(IllegalArgumentException ex) {
        System.out.println("There has been an exception: " + ex.toString());
    }


}

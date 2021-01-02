package com.perkins.simplelog.interceptor;

import com.perkins.simplelog.annotation.SimpleLog;
import com.perkins.simplelog.common.AspectTools;
import com.perkins.simplelog.common.LoggerUtil;
import com.perkins.simplelog.entity.MethodLevelLogContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * @author Perkins
 */
@Service
@Aspect
@Order(50)
public class LoggingInterceptor {
    @Around("@annotation(com.perkins.simplelog.annotation.SimpleLog)")
    public Object intercept(ProceedingJoinPoint point) throws Throwable {
        Logger logger = LogManager.getLogger(point.getTarget().getClass());
        Method method = AspectTools.getMethod(point);

        long startTimes = System.currentTimeMillis();
        long costMills = 0L;
        Object result = null;
        Throwable exception = null;

        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            exception = throwable;
        } finally {
            costMills = System.currentTimeMillis() - startTimes;
        }

        MethodLevelLogContent methodLevelLogContent = new MethodLevelLogContent(costMills, method, point.getArgs(), result, exception);
        LoggerUtil.log(logger,this.getLevel(method), methodLevelLogContent, exception);

        if (exception != null) {
            LoggerUtil.log(logger, Level.ERROR, methodLevelLogContent, exception);
            throw exception;
        } else {
            return result;
        }
    }


    private Level getLevel(Method method) {
        SimpleLog loggableAnnotaion = method.getAnnotation(SimpleLog.class);
        String logLevel = loggableAnnotaion.level();
        return Level.getLevel(logLevel);
    }
}

package com.perkins.simplelog.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Perkins
 */
public class AspectTools {
    /**
     * 获取切点方法
     * @param point
     * @return
     */
    public static Method getMethod(JoinPoint point) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = signature.getName();
        Method method = signature.getMethod();

        if (method.getDeclaringClass().isInterface()) {
            method = point.getClass().getDeclaredMethod(methodName, method.getParameterTypes());
        }

        return method;
    }

    public static String getMethodDeclareInfo(Method method) {
        String calzz = method.getDeclaringClass().getName();
        String methodName = method.getName();
        Class<?> returntype = method.getReturnType();
        Parameter[] parameters = method.getParameters();
        StringBuilder sb = (new StringBuilder(returntype.getName())).append(" ").append(calzz).append("#").append(methodName).append("(");
        Class[] parameterTypes = method.getParameterTypes();
        if (parameters != null && parameters.length > 0) {
            for(int i = 0; i < parameters.length; ++i) {
                sb.append(parameterTypes[i].getSimpleName()).append(" ").append(parameters[i].getName()).append(",");
            }

            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(")");
        return sb.toString();
    }
}

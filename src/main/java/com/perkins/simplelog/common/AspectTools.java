package com.perkins.simplelog.common;

import com.thoughtworks.paranamer.AnnotationParanamer;
import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author Perkins
 */
public class AspectTools {
    private static Paranamer paranamer = new CachingParanamer(new AnnotationParanamer(new BytecodeReadingParanamer()));

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
        String[] parameterNames = paranamer.lookupParameterNames(method, false);
        StringBuilder sb = (new StringBuilder(returntype.getName())).append(" ").append(calzz).append("#").append(methodName).append("(");
        Class[] parameterTypes = method.getParameterTypes();
        if (parameterNames != null && parameterNames.length > 0) {
            for(int i = 0; i < parameterNames.length; ++i) {
                sb.append(parameterTypes[i].getSimpleName()).append(" ").append(parameterNames[i]).append(",");
            }

            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(")");
        return sb.toString();
    }
}

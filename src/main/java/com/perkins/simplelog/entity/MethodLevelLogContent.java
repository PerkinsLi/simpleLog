package com.perkins.simplelog.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.perkins.simplelog.annotation.LoggingExclude;
import com.perkins.simplelog.common.AspectTools;
import com.perkins.simplelog.common.BaseCommonTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Perkins
 */
public class MethodLevelLogContent extends BasicLogContent{
    public final static String NULL_RETURN = "RETURN_NULL";

    @JSONField(ordinal = 10)
    private long costTimeMills;

    @JSONField(ordinal = 11)
    private String method;

    @JSONField(ordinal = 12)
    private Object[] input;

    @JSONField(ordinal = 13)
    private Object output;

    @JSONField(ordinal = 14)
    private Throwable throwable;


    public MethodLevelLogContent(long costTimeMills, Method method, Object[] input, Object output, Throwable throwable) {
        this.costTimeMills = costTimeMills;
        this.method = AspectTools.getMethodDeclareInfo(method);
        this.input = input;

        for (int i = 0; i < method.getParameterAnnotations().length; i++) {
            Annotation[] paraAnnotation = method.getParameterAnnotations()[i];
            if (paraAnnotation != null && paraAnnotation.length > 0) {
                int parLength = paraAnnotation.length;
                for (int j = 0; j < parLength; j++) {
                    Annotation annotation = paraAnnotation[j];
                    if (annotation.equals(LoggingExclude.class)) {
                        this.input[j] = "loging_exclude";
                    }
                }
            }
        }
        this.output = output;
        this.throwable = throwable;
    }

    public long getCostTimeMills() {
        return costTimeMills;
    }

    public String getMethod() {
        return method;
    }

    public Object[] getInput() {
        return input;
    }

    public Object getOutput() {
        return output == null ? NULL_RETURN : output;
    }

    public String getThrowable() {
        return throwable == null ? null : BaseCommonTools.getExceptionSimpleInfo(throwable);
    }
}

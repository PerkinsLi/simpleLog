package com.perkins.simplelog.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.perkins.simplelog.base.ThreadContext;
import com.perkins.simplelog.common.BaseCommonTools;

import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Perkins
 */
public class BasicLogContent {
    private static BasicLogContent.MySerializeFilter mySerializeFilter = new BasicLogContent.MySerializeFilter();
    private String traceId;
    private String messgae;
    private String exception;
    private String hostName;
    private String localIp;

    public BasicLogContent() {
    }

    public String getTraceId() {
        if (this.traceId != null) {
            return this.traceId;
        } else {
            this.traceId = ThreadContext.getInstance().getStackInfo().getTraceId();
            return this.traceId;
        }
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public String getHostName() {
        return BaseCommonTools.getHostName();
    }

    public String getLocalIp() {
        return BaseCommonTools.getIp();
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, mySerializeFilter);
    }

    static class MySerializeFilter implements ValueFilter {
        private static final String _json_serialize_error = "can't_serialize_to_json_string";

        @Override
        public Object process(Object obj, String name, Object value) {
            if (value == null) {
                return null;
            }

            if ("input".equals(name)) {
                Object[] inputs = (Object[]) value;
                List<Object> inputsStringList = new ArrayList<>(inputs.length);
                for (Object input : inputs) {
                    if (input instanceof ServletResponse){
                        continue;
                    }
                    try {
                        JSON.toJSONString(input);
                        inputsStringList.add(input);
                    } catch (Exception e) {
                        inputsStringList.add(_json_serialize_error);
                    }
                }
                return inputsStringList;
            }

            if ("output".equals(name)) {
                try {
                    JSON.toJSONString(value);
                    return value;
                } catch (Exception e) {
                    e.printStackTrace();
                    return _json_serialize_error;
                }
            }
            return value;
        }
    }
}

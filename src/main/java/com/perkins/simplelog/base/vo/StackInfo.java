package com.perkins.simplelog.base.vo;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author Perkins
 */
public class StackInfo {
    private final static String AUTO_GEN_TRC_PREFIX = "auto_generate_trace_";
    protected String traceId;

    public String getTraceId() {
        if (this.traceId == null) {
            this.traceId = AUTO_GEN_TRC_PREFIX + UUID.randomUUID().toString();
        }
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        if (StringUtils.isBlank(traceId)) {
            this.traceId = AUTO_GEN_TRC_PREFIX + UUID.randomUUID().toString();
        } else {
            this.traceId = traceId;
        }
    }
}

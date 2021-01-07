package com.perkins.Demo.service;

import com.perkins.simplelog.annotation.SimpleLog;
import com.perkins.simplelog.common.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Perkins
 */
@Service
public class LogServiceImpl {
    private static final Logger logger = LogManager.getLogger(LogServiceImpl.class);

    @SimpleLog
    public String setName(String name) {
        return name;
    }

    public String getList(){
        LoggerUtil.info(logger, "这是一个自定义日志");
        return null;
    }
}

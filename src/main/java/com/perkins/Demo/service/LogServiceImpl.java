package com.perkins.Demo.service;

import com.perkins.simplelog.annotation.SimpleLog;
import org.springframework.stereotype.Service;

/**
 * @author Perkins
 */
@Service
public class LogServiceImpl {

    @SimpleLog
    public String setName(String name) {
        return name;
    }
}

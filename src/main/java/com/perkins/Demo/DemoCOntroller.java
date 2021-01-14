package com.perkins.Demo;

import com.perkins.Demo.service.LogServiceImpl;
import com.perkins.simplelog.annotation.SimpleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Perkins
 */
@RestController
@RequestMapping("simple_log")
public class DemoCOntroller {
    @Autowired
    LogServiceImpl logService;

    @RequestMapping("log")
    @SimpleLog
    public Integer simpleLog(HttpServletRequest request, String name, String age, String sex){
        logService.setName(name);
        return 12;
    }
}

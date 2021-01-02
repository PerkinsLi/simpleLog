package com.perkins.simplelog.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Perkins
 */
public class BaseCommonTools {
    public static String localHostName;
    public static String localIp;


    public static String getHostName(){
        if (StringUtils.isBlank(localHostName)) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                localHostName = inetAddress.getHostName().toString();
                return localHostName;
            } catch (UnknownHostException e) {
                return "un_known";
            }
        } else {
            return localHostName;
        }
    }

    public static String getIp(){
        if (StringUtils.isBlank(localIp)) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                localIp = inetAddress.getHostAddress().toString();
                return localIp;
            } catch (UnknownHostException e) {
                return "un_known";
            }
        } else {
            return localIp;
        }
    }

    public static String getExceptionSimpleInfo(Throwable e) {
        if (e == null) {
            return null;
        } else {
            JSONObject json = new JSONObject();
            json.put("id", System.identityHashCode(e));
            json.put("class", e.getClass().getName());
            json.put("msg", e.getMessage());
            if (e.getStackTrace() != null && e.getStackTrace().length > 0) {
                json.put("topStack", e.getStackTrace()[0]);
            }

            return json.toJSONString();
        }
    }
}

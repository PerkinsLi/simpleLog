package com.perkins.simplelog.common;

import com.perkins.simplelog.entity.BasicLogContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * @author Perkins
 */
public class LoggerUtil {

    public static void log(Logger logger, Level level, BasicLogContent content, Throwable e) {
        logger.log(level, content.toString(), e);
    }

    public static void log(Logger logger, Level level, String msg, Throwable e) {
        BasicLogContent content = new BasicLogContent();
        content.setMessgae(msg);

        if (e != null) {
            content.setException(BaseCommonTools.getExceptionSimpleInfo(e));
        }

        log(logger, level, content, e);
    }

    public static void debug(Logger logger, String msg) {
        log(logger, Level.DEBUG, (String)msg, (Throwable)null);
    }

    public static void info(Logger logger, String msg) {log( logger, Level.INFO, (String)msg, (Throwable)null);}

    public static void warn(Logger logger, String msg) {
        log( logger, Level.WARN, (String)msg, (Throwable)null);
    }

    public static void warn(Logger logger, String msg, Throwable e) {
        log( logger, Level.WARN, msg, e);
    }

    public static void error(Logger logger, String msg, Throwable e) {
        log( logger, Level.ERROR, msg, e);
    }
}

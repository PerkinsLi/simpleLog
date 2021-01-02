package com.perkins.simplelog.base;

import com.perkins.simplelog.base.vo.StackInfo;

/**
 * @author Perkins
 */
public class ThreadContext {
    private static ThreadContext threadContext;
    private static ThreadLocal<StackInfo> threadLocalStackInfo;

    private ThreadContext () {
        threadLocalStackInfo = new ThreadLocal<>();
    }

    public static ThreadContext getInstance() {
        if (threadContext == null) {
            threadContext = new ThreadContext();
        }

        return threadContext;
    }

    public void saveStackInfo(StackInfo stackInfo){
        threadLocalStackInfo.set(stackInfo);
    }

    public StackInfo getStackInfo(){
        StackInfo stackInfo = threadLocalStackInfo.get();
        if(stackInfo == null) {
            stackInfo = new StackInfo();
            this.saveStackInfo(stackInfo);
        }
        return stackInfo;
    }

    public void clear(){
        threadLocalStackInfo.set(null);
    }
}

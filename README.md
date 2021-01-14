# simplelog

#### 介绍
基于spring aop 实现的日志打印工具，目前只支持log4j2，该工具对系统代码无任何侵入，可随时引用和去除。
该工具打印日志信息格式如下：
```
{"hostName":"MacBook-Pro.local","localIp":"127.0.0.1","traceId":"auto_generate_trace_8f1b8225-14f7-4df5-bbfd-36c11d735b2a","costTimeMills":80,"method":"java.lang.Integer com.perkins.Demo.DemoCOntroller#simpleLog(String name,String age,String sex)","input":["元帅","10","男"],"output":12}
```

#### maven引入
```
        <dependency>
            <groupId>com.github.perkinsli</groupId>
            <artifactId>simplelog</artifactId>
            <version>1.0.4</version>
        </dependency>
```

#### 使用说明
在方法上加上@Simplelog注解即可

``` java
    @SimpleLog
    public String setName(String name) {
        return name;
    }
```

代码内打印日志使用LoggerUtil工具类
``` java
private static final Logger logger = LogManager.getLogger(LogServiceImpl.class);

    public String getList(){
        LoggerUtil.info(logger, "这是一个自定义日志");
        return null;
    }
```

代码内日志打印可以使用LoggingUtil类。
#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

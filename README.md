# simplelog

#### 介绍
基于spring aop 实现的日志打印工具，目前只支持log4j2

#### maven引入
```
        <dependency>
            <groupId>com.github.perkinsli</groupId>
            <artifactId>simplelog</artifactId>
            <version>{version}</version>
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

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

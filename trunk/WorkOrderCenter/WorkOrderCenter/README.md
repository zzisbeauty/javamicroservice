水务-JAVA微服务架构项目模板
===============

当前最新版本： 1.0.0（发布日期：2025.1.1）


##### 项目说明

|分组| 服务名                | 说明     | 备注|
|----|--------------------|------------------------|----|
|示例模块| `test-sample`  | 示例模块 |示例模块
|公共集成服务| `dubbo-facade` | dubbo防腐层 |dubbo接口注册
|公共集成服务| `common-core` | 基础服务 |集成基础支撑服务




技术文档
-----------------------------------
hutool工具类: https://www.hutool.cn/docs/#/




Docker启动项目
-----------------------------------

后台目录结构
-----------------------------------
```
项目结构
├─JavaMicroService（父POM： 项目依赖、modules组织）
│  ├─base-core（共通模块： 工具类、公用方法，底层公用处理）
│  │  ├─annotation 公共注解
│  │  ├─aspect     切片注入
│  │  ├─autoapi    网关自动注册 
│  │  ├─base       公共处理类 
│  │  ├─codegenerator   代码生成器
│  │  ├─common     全局常量、枚举值
│  │  ├─config     全局配置
│  │  ├─exception  全局异常
│  │  ├─filter     过滤器
│  │  ├─mybatis     mybatis处理类
│  │  ├─oldquery    兼容老框架查询处理
│  │  ├─util       常用工具类
│  │  │   ├─CommonUtils        通用工具
│  │  │   ├─DateUtils          时间操作定义
│  │  │   ├─MinioUtils         文件上传工具
│  │  │   ├─oConvertUtils      数据转换工具
│  │  │   ├─SpringContextUtils spring上下文工具
│  │  │   ├─SqlInjectionUtil   sql注入处理工具
```

技术架构：
-----------------------------------
#### 开发环境

- 语言：Java JDK 17.0.13

- IDE(JAVA)： IDEA (必须安装lombok插件 )

- 依赖管理：Maven 3.6.3

- 缓存：Redis

- 数据库： PostgreSql


#### 后端
2023.x 分支对应的是 Spring Cloud 2023 与 Spring Boot 3.2.x，最低支持 JDK 17。
- Spring Boot 3.2.5
- Spring Cloud 2023.0.3
- Spring Cloud Alibaba 2023.0.1.0

- 持久层框架：MybatisPlus 3.5.1

- 数据库连接池：Druid 1.1.22

- 日志打印：logback

- 其他：easyExcel, fastjson，poi，Swagger-ui，quartz, lombok（简化代码）等。



### 功能模块
```
├─配置管理
│  └─更多功能开发中。。
├─身份认证
│  └─更多功能开发中。。
├─API网关
│  └─更多功能开发中。。
```

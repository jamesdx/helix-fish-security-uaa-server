# Spring Boot Data JPA 开发文档
## 如何配置 Spring Boot Data JPA - 关系型数据库
* 添加 Maven 依赖: 
1. 添加 `spring-boot-starter-data-jpa`，这个 配置 自动添加 `JPA` , `Hibernate` , `Hikari` ( `Spring Boot 2.x`默认支持 `Hikari` ,而不是 `Tomcate`)  
    ```xml
    <!-- Spring JPA-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```
2. 添加数据库驱动 - `MySql`
    ```xml
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```
* 配置 数据源
1.数据源通用配置：以 `spring.datasource.*` 形式，主要用于配置所有数据源都要使用公用配置，例如：数据库链接地址、用户名、密码等。这里就不做过多说明了，通常就这些配置：
```properties
spring:
  datasource:
    url: ${YOUR_MYSQL_HOST}
    username: ${YOUR_MYSQL_USERNAME}
    password: ${YOUR_MYSQL_PASSWORD}
    driver-class-name: ${YOUR_DATABASE_DRIVER}
```
2.数据库连接池配置：以 `spring.datasource.<数据源名称>.*` ，比如：Hikari的配置参数就是spring.datasource.hikari.*形式， 这个针对个性化数据连接池进行配置，可以用于数据库连接层次的性能优化。
```properties
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.idle-timeout=500000
spring.datasource.hikari.max-lifetime=540000
spring.datasource.hikari.connection-timeout=60000
spring.datasource.hikari.connection-test-query=SELECT 1
```
*配置含义说明：*
* spring.datasource.hikari.minimum-idle: 最小空闲连接，默认值10，小于0或大于maximum-pool-size，都会重置为maximum-pool-size
* spring.datasource.hikari.maximum-pool-size: 最大连接数，小于等于0会被重置为默认值10；大于零小于1会被重置为minimum-idle的值
* spring.datasource.hikari.idle-timeout: 空闲连接超时时间，默认值600000（10分钟），大于等于max-lifetime且max-lifetime>0，会被重置为0；不等于0且小于10秒，会被重置为10秒。
* spring.datasource.hikari.max-lifetime: 连接最大存活时间，不等于0且小于30秒，会被重置为默认值30分钟.设置应该比mysql设置的超时时间短
* spring.datasource.hikari.connection-timeout: 连接超时时间：毫秒，小于250毫秒，否则被重置为默认值30秒
* spring.datasource.hikari.connection-test-query: 用于测试连接是否可用的查询语句
* 配置持久层框架 JPA + Hibernate
* 配置 数据库驱动
* 添加 Flyway 
* 初始化数据库
## 如何配置 Spring Boot Data Redis
在 Spring Boot 2.x 版本中，SpringBoot默认集成Lettuce。
* 添加 Spring Boot Data Starter
    ```xml
    <!--Redis-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    ```
* 配置 Redis Server 信息
1.Redis基本配置， 配置 Redis 主机IP， 端口，使用数据库，超时时间。
    ```properties
      redis:
        host: ${YOUR_REDIS_SERVER_HOST}
        database: ${REDIS_DATABASE}
        port: ${REDIS_PORT}
        timeout: ${REDIS_TIMEOUT}
    ```
2.Letuce 配置
```properties
lettuce:
 pool:
   max-active: 10  #连接池最大连接数  
   max-idle: 8     #连接池中最大空闲连接数
   max-wait: -1ms  #连接池最大等待阻塞时间
   min-idle: 0     #连接池中最小空闲数
```

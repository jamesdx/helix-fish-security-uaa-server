#Spring Boot Log 开发文档
## Spring Log 配置
### 默认 Spring Boot Log 
默认情况下，Spring Boot会用SLF4J + Logback来记录日志，并用INFO级别输出到控制台。
## 使用 SLF4J + LOG4j2 + Lombok
1. 排除 Log4j , 加入 Log4j2
    ```xml
    <!--Spring Boot SL4J + Log4j2-->
    <dependency>
        <!-- 排除 spring-boot-starter-logging -->
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-logging</artifactId>
        <exclusions>
            <exclusion>
                <groupId>*</groupId>
                <artifactId>*</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
    ```
   
## Spring Boot Log 配置
1. 添加 Spring Boot Log 基础配置
2. 添加 Logback 配置文件
## Spring Boot Log 何使用
    * Logger 方式
        ```java
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        Logger logger = LoggerFactory.getLogger(getClass());
        //private static final Logger logger = LoggerFactory.getLogger(ResponseAop.class);
        ```
    * @Sl4j 注解
        * 添加 Lobbox 配置
        ```xml
        <!-- lombok依赖 -->
        <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <optional>true</optional>
          <version>1.18.0</version>
        </dependency>
        ```
      

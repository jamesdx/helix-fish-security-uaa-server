# Spring Boot 开发文档
## 如何集成 Spring Boot 到项目
### 单独项目
- 添加Maven依赖
    ```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.12.RELEASE</version>
    </parent>
    ```
 -创建 Spring Boot Application 类
 1. 使用 `@SpringBootApplication` 注解
 2. 编写 `main` 方法，并使用 `SpringAppliction` 的 `run` 方法 
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelixFishSecurityUaaAuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelixFishSecurityUaaAuthServerApplication.class,args);
    }
}

```
 -创建 Spring Boot 配置文件
这个文件名字是 `application.yml` 或者  `application.properties` , 它存放在 `resources` 目录，第一步可以简单配置 Server 端口号
```properties
server:
  port: 8081
```
### 父子项目

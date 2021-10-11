#Spring Security OAuth2.0 文档
## OAuth 2.0

## 如何使用 Spring Security OAuth2.0
* 添加 Spring Security OAuth2.0 依赖。
在 Spring Boot 和 Spring Cloud 配置的基础上添加 Spring Cloud OAuth2.0 Starter 即可
    ```xml
    <!-- spring cloud oauth2 starter -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-oauth2</artifactId>
    </dependency>
    ```
* 创建认证服务器
1.创建 认证服务器 配置类:  
继承 `AuthorizationServerConfigurerAdapter` 并 重写3个 `config` 方法：
    ```java
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
    import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
    import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
    import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
    import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
    
    /**
     * 认证服务器配置
     */
    @Configuration
    @EnableAuthorizationServer
    public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        }
    
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        }
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        }
    
    }
    ```
  
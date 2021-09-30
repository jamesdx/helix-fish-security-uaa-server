# Spring Boot Test 开发文档
## 集成 Spring Boot Test 
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
## Unit Testing
一个 Unit Testing Java 类模版，它基于 Spring Boot Test
```java
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UaaUserServiceTest {
    @Before
    public void initDate(){
        
    }

    @Test
    public void verify_findUserByUserAndPassword(){
        
    }

    @After
    public void deleteTestingData(){
       
    }
}

```
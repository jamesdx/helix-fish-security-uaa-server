package org.helix.fish.security.uaa.user;

import org.helix.fish.security.uaa.user.entity.UaaUsersEntity;
import org.helix.fish.security.uaa.user.service.impl.UaaUserServiceImpl;
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
    @Autowired
    private UaaUserServiceImpl uaaUserService;
    private String testUserName = "Allen001";
   @Before
    public void initDate(){
        UaaUsersEntity uaaUsersEntity = new UaaUsersEntity();
        uaaUsersEntity.setUserName(testUserName);
        uaaUsersEntity.setPassword("password");
        uaaUserService.saveUser(uaaUsersEntity);
    }

    @Test
    public void verify_findUserByUserAndPassword(){
        uaaUserService.getUserAuthorizationByUserNameAndPassword("Allen001","password");
    }

    @After
    public void deleteTestingData(){
       uaaUserService.removeUserByUserName(testUserName);
    }
}

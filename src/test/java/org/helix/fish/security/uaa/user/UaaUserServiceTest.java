package org.helix.fish.security.uaa.user;

import org.helix.fish.security.uaa.user.entity.UaaAuthorityEntity;
import org.helix.fish.security.uaa.user.entity.UaaRoleEntity;
import org.helix.fish.security.uaa.user.entity.UaaUserEntity;
import org.helix.fish.security.uaa.user.service.UaaAuthorityService;
import org.helix.fish.security.uaa.user.service.UaaRoleService;
import org.helix.fish.security.uaa.user.service.impl.UaaUserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UaaUserServiceTest {
    @Autowired
    private UaaUserServiceImpl uaaUserService;
    @Autowired
    private UaaRoleService uaaRoleService;
    @Autowired
    private UaaAuthorityService uaaAuthorityService;

    private String testUserName = "Allen001";
    private String testRoleName001 = "testRoleName001";
    private String testRoleName002 = "testRoleName002";
    private String testRoleName003 = "testRoleName003";
    private String authorityName001 = "authorityName001";

    @Before
    @Transactional
    public void initDate() {
        UaaUserEntity uaaUserEntity = new UaaUserEntity();
        uaaUserEntity.setUserName(testUserName);
        uaaUserEntity.setPassword("password");


        Set users = new HashSet<UaaUserEntity>();
        users.add(uaaUserEntity);


        UaaAuthorityEntity authorityEntity = new UaaAuthorityEntity();
        authorityEntity.setAuthorityName(authorityName001);


        Set authorities = new HashSet<UaaAuthorityEntity>();
        authorities.add(uaaAuthorityService);

        UaaRoleEntity roleEntity = new UaaRoleEntity();
        roleEntity.setRoleName(testRoleName001);

        UaaRoleEntity roleEntityA = new UaaRoleEntity();
        roleEntityA.setRoleName(testRoleName002);

        UaaRoleEntity roleEntityB = new UaaRoleEntity();
        roleEntity.setRoleName(testRoleName003);

        roleEntity.setUsers(users);

        uaaRoleService.saveRole(roleEntity);
        uaaRoleService.saveRole(roleEntityA);
        uaaRoleService.saveRole(roleEntityB);
        Set roles = new HashSet<UaaRoleEntity>();
        roles.add(roleEntity);
        roles.add(roleEntityA);
        roles.add(roleEntityB);

        uaaUserEntity.setRoles(roles);
        uaaUserService.saveUser(uaaUserEntity);
//        authorityEntity.setRoles(roles);
        uaaAuthorityService.saveAuthority(authorityEntity);
    }

    @Test
    public void verify_findUserByUserAndPassword() {
        UaaUserEntity uaaUserEntity = uaaUserService.getUserAuthorizationByUserNameAndPassword("Allen001", "password");
        uaaUserEntity.getRoles().size();

    }

    //    @After
    public void deleteTestingData() {
        uaaUserService.removeUserByUserName(testUserName);
    }
}

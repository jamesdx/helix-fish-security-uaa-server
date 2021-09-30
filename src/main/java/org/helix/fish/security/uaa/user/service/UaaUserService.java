package org.helix.fish.security.uaa.user.service;


import org.helix.fish.security.uaa.user.entity.UaaUsersEntity;

public interface UaaUserService {
    UaaUsersEntity getUserAuthorizationByUserNameAndPassword(String userName,String Password);

    void removeUserByUserName(String Username);

    UaaUsersEntity saveUser(UaaUsersEntity uaaUsersEntity);
}

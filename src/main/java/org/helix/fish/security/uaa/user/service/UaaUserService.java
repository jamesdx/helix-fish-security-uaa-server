package org.helix.fish.security.uaa.user.service;


import org.helix.fish.security.uaa.user.entity.UaaUserEntity;

public interface UaaUserService {
    UaaUserEntity getUserAuthorizationByUserNameAndPassword(String userName, String Password);

    void removeUserByUserName(String Username);

    UaaUserEntity saveUser(UaaUserEntity uaaUserEntity);
}

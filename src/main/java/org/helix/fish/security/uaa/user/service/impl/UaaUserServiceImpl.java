package org.helix.fish.security.uaa.user.service.impl;

import org.helix.fish.security.uaa.user.entity.UaaUsersEntity;
import org.helix.fish.security.uaa.user.repository.UaaUserRepository;
import org.helix.fish.security.uaa.user.service.UaaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UaaUserServiceImpl implements UaaUserService {
    @Autowired
    private UaaUserRepository uaaUserRepository;

    @Override
    public UaaUsersEntity getUserAuthorizationByUserNameAndPassword(String userName, String password) {
        UaaUsersEntity uaaUsersEntity = uaaUserRepository.findByUserNameAndPassword( userName,password);

        return uaaUsersEntity;
    }

    @Override
    public void removeUserByUserName(String userName) {
        uaaUserRepository.deleteByUserName(userName);
    }

    @Override
    public UaaUsersEntity saveUser(UaaUsersEntity uaaUsersEntity) {
        return uaaUsersEntity = uaaUserRepository.save(uaaUsersEntity);
    }
}

package org.helix.fish.security.uaa.user.service.impl;

import org.helix.fish.security.uaa.user.entity.UaaUserEntity;
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
    public UaaUserEntity getUserAuthorizationByUserNameAndPassword(String userName, String password) {
        UaaUserEntity uaaUserEntity = uaaUserRepository.findByUserNameAndPassword( userName,password);

        return uaaUserEntity;
    }

    @Override
    public void removeUserByUserName(String userName) {
        uaaUserRepository.deleteByUserName(userName);
    }

    @Override
    public UaaUserEntity saveUser(UaaUserEntity uaaUserEntity) {
        return uaaUserEntity = uaaUserRepository.save(uaaUserEntity);
    }
}

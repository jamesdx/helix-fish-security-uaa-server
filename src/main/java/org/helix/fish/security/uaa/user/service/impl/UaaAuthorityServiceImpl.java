package org.helix.fish.security.uaa.user.service.impl;

import org.helix.fish.security.uaa.user.entity.UaaAuthorityEntity;
import org.helix.fish.security.uaa.user.repository.UaaAuthorityRepository;
import org.helix.fish.security.uaa.user.service.UaaAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UaaAuthorityServiceImpl implements UaaAuthorityService {

    @Autowired
    private UaaAuthorityRepository uaaAuthorityRepository;

    public UaaAuthorityEntity saveAuthority(UaaAuthorityEntity uaaAuthorityEntity) {

        uaaAuthorityEntity = uaaAuthorityRepository.save(uaaAuthorityEntity);

        return uaaAuthorityEntity;
    }
}

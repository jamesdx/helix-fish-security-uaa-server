package org.helix.fish.security.uaa.user.service.impl;

import org.helix.fish.security.uaa.user.entity.UaaRoleEntity;
import org.helix.fish.security.uaa.user.repository.UaaRolesRepository;
import org.helix.fish.security.uaa.user.service.UaaRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UaaRoleServiceImpl implements UaaRoleService {
    @Autowired
    private UaaRolesRepository uaaRolesRepository;

    @Override
    public UaaRoleEntity saveRole(UaaRoleEntity uaaRoleEntity) {
        uaaRoleEntity = uaaRolesRepository.save(uaaRoleEntity);
        return uaaRoleEntity;
    }
}

package org.helix.fish.security.uaa.user.service;

import org.helix.fish.security.uaa.user.entity.UaaAuthorityEntity;

public interface UaaAuthorityService {
    UaaAuthorityEntity saveAuthority(UaaAuthorityEntity uaaAuthorityEntity);
}

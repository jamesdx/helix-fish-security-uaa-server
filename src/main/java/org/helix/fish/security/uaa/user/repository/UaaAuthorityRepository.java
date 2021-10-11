package org.helix.fish.security.uaa.user.repository;

import org.helix.fish.security.uaa.user.entity.UaaAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UaaAuthorityRepository extends JpaRepository<UaaAuthorityEntity,Long> {

}

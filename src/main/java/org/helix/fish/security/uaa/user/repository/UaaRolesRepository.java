package org.helix.fish.security.uaa.user.repository;

import org.helix.fish.security.uaa.user.entity.UaaRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UaaRolesRepository extends JpaRepository<UaaRoleEntity,Long> {
}

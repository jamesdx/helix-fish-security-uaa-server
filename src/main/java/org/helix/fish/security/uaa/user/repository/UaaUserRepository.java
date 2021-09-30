package org.helix.fish.security.uaa.user.repository;

import org.helix.fish.security.uaa.user.entity.UaaUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UaaUserRepository extends JpaRepository<UaaUsersEntity,Long> {
    UaaUsersEntity findByUserNameAndPassword(String userName,String password);

    void deleteByUserName(String userName);
}

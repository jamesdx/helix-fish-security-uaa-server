package org.helix.fish.security.uaa.user.repository;

import org.helix.fish.security.uaa.user.entity.UaaUserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UaaUserRepository extends JpaRepository<UaaUserEntity, Long> {
    //    @EntityGraph(value="user-with-role", type = EntityGraph.EntityGraphType.FETCH )
    @EntityGraph(value = "user-with-role")
    UaaUserEntity findByUserNameAndPassword(String userName, String password);

    void deleteByUserName(String userName);
}

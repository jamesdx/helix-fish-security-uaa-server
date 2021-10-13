package org.helix.fish.security.uaa.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "uaa_role")
public class UaaRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;


    @ManyToMany(mappedBy = "roles")
    private Set<UaaUserEntity> users = new HashSet<UaaUserEntity>();

    @ManyToMany
    @JoinTable(name = "uaa_role_authority"
            , joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id")
            , inverseJoinColumns = @JoinColumn(name = "authority_id",referencedColumnName = "authority_id"))
    private Set<UaaAuthorityEntity> authorityEntities = new HashSet<UaaAuthorityEntity>();

}

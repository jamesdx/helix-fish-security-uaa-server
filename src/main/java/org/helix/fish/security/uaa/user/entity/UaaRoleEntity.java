package org.helix.fish.security.uaa.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="uaa_roles")
public class UaaRoleEntity extends BasicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UaaUserEntity> users;

    @ManyToMany(mappedBy = "roles" ,fetch = FetchType.LAZY)
    private Set<UaaAuthorityEntity> authorities;


}

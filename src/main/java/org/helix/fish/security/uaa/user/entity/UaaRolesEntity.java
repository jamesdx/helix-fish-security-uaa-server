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
public class UaaRolesEntity extends BasicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UaaUsersEntity> users;

    @ManyToMany(mappedBy = "roles" ,fetch = FetchType.EAGER)
    private Set<UaaAuthoritiesEntity> authorities;


}

package org.helix.fish.security.uaa.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "uaa_users")
public class UaaUsersEntity extends BasicEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToMany(targetEntity = UaaRolesEntity.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "uaa_users_roles"
            , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<UaaRolesEntity> roles;

}

package org.helix.fish.security.uaa.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "uaa_authorities")
public class UaaAuthoritiesEntity extends BasicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long authorityId;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UaaRolesEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "uaa_roles_authorities"
            , joinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "authority_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<UaaRolesEntity> roles;
}

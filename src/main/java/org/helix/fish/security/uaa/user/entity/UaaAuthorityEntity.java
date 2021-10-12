package org.helix.fish.security.uaa.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "uaa_authorities")
public class UaaAuthorityEntity extends BasicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long authorityId;

    @Column(name = "authority_name")
    private String authorityName;

    @ManyToMany(mappedBy = "authorityEntities")
    private Set<UaaRoleEntity> roles = new HashSet<UaaRoleEntity>();
}

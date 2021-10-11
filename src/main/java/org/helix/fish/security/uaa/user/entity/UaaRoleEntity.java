package org.helix.fish.security.uaa.user.entity;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonManagedReference;

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

    @JsonManagedReference
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<UaaUserEntity> users;
//
//    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
//    private Set<UaaAuthorityEntity> authorities;


}

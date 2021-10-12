package org.helix.fish.security.uaa.user.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "uaa_user")
@NamedEntityGraph(
        name = "user-with-role"
        , attributeNodes = {
                @NamedAttributeNode("roles"),
                @NamedAttributeNode(value="roles", subgraph = "authorityEntities")
        }
        , subgraphs = {@NamedSubgraph(name = "authorityEntities" ,attributeNodes = {@NamedAttributeNode("authorityEntities")})
        ,@NamedSubgraph(name = "roles", attributeNodes = @NamedAttributeNode(value = "role",subgraph = "authorityEntities"))}
)
public class UaaUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "uaa_user_role"
            , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<UaaRoleEntity> roles;

}

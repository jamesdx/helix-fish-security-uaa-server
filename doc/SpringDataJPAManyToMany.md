# 如何使用 JPA 实现 多对多关系
* JPA 多对多实现原理  
在使用 `JPA` 实现多对多关系时候,JAP 会使用一个关联表来维护多对多关系。通常我们建议使用 `@ManyToMany` 和  `@JoinTable` 来实现对这个表的维护。
其中，`@ManyToMany` 负责维护多对多关系，`@JoinTable` 负责定制关联表。
    * 关于 `多对多中间表`  
    默认情况下，表名默认是：主表名+下划线+从表名。(主表是指关系维护端对应的表,从表指关系被维护端对应的表)。这个关联表只有两个外键字段，分别指向主表ID和从表ID。
    字段的名称默认为：主表名+下划线+主表中的主键列名，从表名+下划线+从表中的主键列名。
    * 关于 `多对多关系维护`  
    在 JPA 处理多对多关系时候，需要选择一个`主对象` 来维护这个多对多关系，也就是谁来负责关系的添加、删除、修改和关联。  
* `JPA` 多对多实现案例   
我们以 RBAC权限模型下， User, Role, Authority 之间的多对多关系为例：
    * 第一步，定义`主对象` 
    首先使用 @Entity 注解表明这是一个 JPA 的 Entity Bean，然后使用 `@Table` 注解声明 Entity Bean 关联的 数据库表
    ，为了避免JPA 的 `N+1 和 N+2` 问题，在 `主对象` 使用上， 我们使用 `JPA Entity Graph` 。
        ```xml
        @NamedEntityGraph(
        name = "user-with-role"
        , attributeNodes = {
                @NamedAttributeNode("roles"),
                @NamedAttributeNode(value="roles", subgraph = "authorityEntities")
        }
        , subgraphs = {@NamedSubgraph(name = "authorityEntities" ,attributeNodes = {@NamedAttributeNode("authorityEntities")})
        ,@NamedSubgraph(name = "roles", attributeNodes = @NamedAttributeNode(value = "role",subgraph = "authorityEntities"))}
        )
      public class UaaUserEntity {}
        ```
    * 第二步, 在`主对象`定义多对多:  
        User 和 Role 的多对多关系， 我们规定 User 为 `主对象`, 我们在`多对多关系属性`上使用两个核心注解,
        需要注意的是这个属性我们使用的 `Set` 而不是 `List` 。 具体参加 `参加 JAP 最佳实践`
        * @ManyToMany 表记这是一个多对多关系。 
            * fetch, 定义了  fetchType.LAZY 懒加载级联方式。
        * @JoinTable 定义了 `多对多关系表`的表结构，
            * name 定义表名
            * JoinColumns , 指定主表的外建，关系表中的那个外建对应主表中那个字段
            * inverseJoinColumns ， 指定从表的外建，关系表中的那个外建对应从表中那个字段
        ```java
        // User Class
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "uaa_user_role"
                , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
                , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
        private Set<UaaRoleEntity> roles; 
        ```
    * 第三步：定义`从对象` 及定义多对多关系:
        Role 为 从对象，我们在个对象的类使用  @ManyToMany 注解表名这是个多对对关系,同时使用 `mappedBy`属性来声明找个对象是从对象，
        并且多对多关系由 User 来维护  
        ```java
        // Role Class
        @ManyToMany(mappedBy = "roles")
        private Set<UaaUserEntity> users = new HashSet<UaaUserEntity>();
        ```
    * 第四步：定义 Repository 组件, 实现 CURD
        编写 Repository 组件，一般我们建议基础 Spring Data 提供的 JpaRepository ， 在查询方法请使用 Entity Graph ，来避免 N+1 的问题。
        * 查询方法：  
            ```java
            public interface UaaUserRepository extends JpaRepository<UaaUserEntity, Long> {
                @EntityGraph(value = "user-with-role")
                UaaUserEntity findByUserNameAndPassword(String userName, String password);
            }
            ```
        * 添加方法
        由于继承了 JpaRepository ， 这样我们自己定义的功能具备了常用的 CURD方法， 但是需要注意是，在 `CUD` 过程，我们要添加维护多对多关系的逻辑代码
            ```java
            Set roles = new HashSet<UaaRoleEntity>();
            roles.add(roleEntity);
            roles.add(roleEntityA);
            roles.add(roleEntityB);
            // 维护多对多关系逻辑代码。
            uaaUserEntity.setRoles(roles);
            uaaUserService.saveUser(uaaUserEntity);
            ``` 
 
#Spring Boot Data JPA 开发文档
## 如何使用 JPA
### 如何使用 JPA 实现 多对多关系
### 如何使用 JPA 实现一对多
https://www.cnblogs.com/ealenxie/p/9800818.html

### N+1 或者 N+2 问题
### 多对多及联保存


## JPA 基础
### JPA 对象映射关系
JPA 对象映射关系主要有 5 中，一对多，多对一，多对多 和 方向的组合，具体如下  
* 映射单向多对一的关联关系  
* 映射单向一对多的关联关系  
* 映射双向多对一的关联关系  
* 映射双向一对一的关联关系  
* 映射双向多对多的关联关系  
### JPA 核心注解

* 标记主键注解 `@Id` 
* 主见生成策略注解 `@GeneratedValue`
* `@NamedEntityGraph` 
* `@OneToMany`
* `@OneToOne`

* `@ManyToMany`
    * 作用：用于映射多对多关系
    * 属性：
       * cascade：配置级联操作。
       * fetch：配置是否采用延迟加载。
       * targetEntity：配置目标的实体类。映射多对多的时候不用写。
* `@JoinTable`
    * 作用：针对中间表的配置
    * 属性：
        * name：配置中间表的名称
        * joinColumns：中间表的外键字段关联当前实体类所对应表的主键字段                          
        * inverseJoinColumn：中间表的外键字段关联对方表的主键字段
* `@JoinColumn`
    * 作用：用于定义主键字段和外键字段的对应关系。
    * 属性：
        * name：指定外键字段的名称
        * referencedColumnName：指定引用主表的主键字段名称
        * unique：是否唯一。默认值不唯一
        * nullable：是否允许为空。默认值允许。
        * insertable：是否允许插入。默认值允许。
        * updatable：是否允许更新。默认值允许。
        * columnDefinition：列的定义信息。
* `@MappedSuperclass`
定义了这个类能够被其他实体类基础，同时这个类并不会在数据中专门建立一张表。  
基于代码复用和模型分离的思想，在项目开发中使用JPA的@MappedSuperclass注解将实体类的多个属性分别封装到不同的非实体类中.
* `@EntityListeners`
@EntityListeners可以被用来监听模型事件（数据创建，修改，删除甚至自定义事件），一般是注册`AuditingEntityListener`这个事件。



### mappedBy 属性
在及联关系两个中，`mappedBy` 用来标注那个对象负责维护这种及联的数据库字段，也就是谁拥有及联关系， 那么什么叫拥有关联关系呢，假设是双向一对一的话，
那么拥有关系的这一方有建立、解除和更新与另一方关系的能力。而另一方没有，只能被动管理。  
除非关系是单向的，否则是必需的。  
mappedBy是OneToOne、OneToMany和ManyToMany这三种关联关系的属性。


### Cascade 级联关系 - 针对 CUD 操作
主要设置JPA关于级联对象的 创建，删除，修改操作，在 JPA中， cascade 级联关系类型：
1. CascadeType.REFRESH：级联刷新，当多个用户同时作操作一个实体，为了用户取到的数据是实时的，在用实体中的数据之前就可以调用一下refresh()方法
2. CascadeType.REMOVE：级联删除，当调用remove()方法删除Order实体时会先级联删除OrderItem的相关数据
3. CascadeType.MERGE：级联更新，当调用了Merge()方法，如果Order中的数据改变了会相应的更新OrderItem中的数据
4. CascadeType.ALL：包含以上所有级联属性
5. CascadeType.PERSIST：级联保存，当调用了Persist() 方法，会级联保存相应的数据
需要注意的是，CascadeType.ALL要谨慎使用，为了达到数据同步，很多人喜欢用CascadeType.ALL来实现。但上面订单和商品的例子就不适用。
### Fetch - 针对 R 操作
主要针对 JDA的查询操作，在JPA 联级属性中的 fetch 是设置什么时候加载联级关系的属性值，它分为 `FetchType.LAZY` 懒加载和`FetchType.EAGER`急加载,具体说明如下：  

* FetchType.LAZY（懒加载）：
被定义的属性所关联的数据不会马上从数据库中加载，在同一个session中，什么时候要用，就什么时候取(再次访问数据库)。但是在session关闭后，就不能再取了，会报异常：org.hibernate.LazyInitializationException。解决这个异常就需要将LAZY转为EAGER。一般@OneToMany为LAZY

* FetchType.EAGER（急加载）：
被定义的属性所关联的数据会马上加载到内存，所以session是否关闭都可以取出来。一般@ManyToOne为EAGER。
## JPA Audit  - JPA 审计
1. 开启 JPA Audit 审计配置
2.
## 如何使用 JPA Repository
### 常用方法
1. `save` 方法
2. `saveAndFlush` 方法
## 如何构建一个 JPA 实例对象父类 - 
https://cloud.tencent.com/developer/article/1799671?from=article.detail.1700718
1. 使用 @MappedSuperclass 注解
2. 使用 @EntityListeners 
3. JAP Audit
4. 重写 toString() 方法
5. 填写分组

## 如何为 JPA 指定主键生成策略
###

## JPA 开发最佳实践
1. 把SQL封装到注解方式
2. 直接使用方法拼接查询
3. JpaRepository<T, ID>
该方法封装了常用的增删改查。
4. JpaSpecificationExecutor< T>
封装了查询一条数据、条件查询、分页查询、排序查询、计数查询。
5. N+1 解决 N+1 问题
* 问题描述
当我们使用JPA提供给我们的find方法时，如果查询出来的对象关联着另外10个对象，
那么JPA将会发送1+10次查询（这个对象本身要查询一次，然后每个关联对象再查询一次）
* 解决方案
1. `NameEntityGraph`
https://zhuanlan.zhihu.com/p/139998091W
2. `FetchType.EAGE + FetchMode.JOIN`

@EntityGraph：解决懒加载的查询N+1问题，提升查询效率。
6.ManyToMany：一对多
注意：防止无限递归，可以使用toString来解决  
    * 问题描述：  
    爆栈的原因通常是因为递归或者无限循环，上面的代码造成堆栈溢出的直接原因是递归，下面来分析一下造成递归的原因：
    定义类InfiniteRecursion，本来希望通过toString方法打印出当前对象的内存地址，获取当前对象当然会想到this关键字。在toString方法内，Java编译器发现一个字符串后面跟着一个加号，加号后面的对象不是String类型，
    所以编译器试着自动将this转换为字符串类型，也就是调用this.toString()方法，偏偏this所指代的这个对象重新实现了toString方法，于是造成循环不断地调用toString方法，这样就发生了递归调用。  
    * Java 代码：
        ```java
        public class InfiniteRecursion {
            public String toString(){
                return "InfiniteRecursion address "+ this + "\n"; 
            }
            
            public static void main(String[] args) {      
                InfiniteRecursion obj = new InfiniteRecursion();
                System.out.println(obj);
            }
        }
        ```
   
7.自定义SQL
@Query 注解
8. 重写 hashCode,equals,toString 方法
## JPA 配置
* 常用配置：
```properties
# 是否开启JPA Repositories，缺省: true
spring.data.jpa.repositories.enabled=true

# JPA数据库类型，默认可以自动检测，也能通过设置spring.jpa.database-platform达到同样效果
spring.jpa.database=ORACLE

# 数据库平台，常见的值如：
# org.hibernate.dialect.Oracle10gDialect
# org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect

# 是否使用JPA初始化数据库，可以在启动时生成DDL创建数据库表，缺省为false
spring.jpa.generate-ddl = false

# 更细粒度的控制JPA初始化数据库特性，用来设定启动时DDL操作的类型，下文有详细介绍
# 内嵌数据库 hsqldb, h2, derby的缺省值为create-drop
# 非内嵌数据库的缺省值为none
spring.jpa.hibernate.ddl-auto = update

# Hibernate操作时显示真实的SQL, 缺省：false
spring.jpa.show-sql = true

# Hibernate 5 隐含命名策略类的全限定名
spring.jpa.hibernate.naming.implicit-strategy= 

# Hibernate 5 物理命名策略类的全限定名
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl 

# Use Hibernate's newer IdentifierGenerator for AUTO, TABLE and SEQUENCE.
spring.jpa.hibernate.use-new-id-generator-mappings= 

# 额外设置JPA配置属性，通常配置项是特定实现支持的，如Hibernate常有下面的几条配置项
spring.jpa.properties.* = 

# 将SQL中的标识符（表名，列名等）全部使用引号括起来
spring.jpa.properties.hibernate.globally_quoted_identifiers=true

# 日志记录执行的SQL
spring.jpa.properties.hibernate.show_sql = true

# 是否将格式化SQL日志
spring.jpa.properties.hibernate.format_sql = true

# 是否注册OpenEntityManagerInViewInterceptor. 绑定JPA EntityManager 到请求线程中. 默认为: true.
spring.jpa.open-in-view=true

# Hibernate 4 命名策略的全类名，Hibernate 5不再适用
spring.jpa.hibernate.naming-strategy=
```
* JPA 打印SQL
1. 修改 `application.yml`
```properties
# 日志记录执行的SQL
spring.jpa.properties.hibernate.show_sql = true

# 是否将格式化SQL日志
spring.jpa.properties.hibernate.format_sql = true

# Hibernate操作时显示真实的SQL, 缺省：false
spring.jpa.show-sql = true

```
2. `Logback` 配置文件
```xml
<loggers>
    <!-- 打印 JPA 的 SQL 输入参数 -->
    <logger name="org.hibernate.type.descriptor.sql.BasicBinder" level="TRACE">
        <appender-ref ref="Console"/>
    </logger>
    <root level="INFO">
        <appender-ref ref="Console"/>
    </root>
</loggers>
```
## Spring Boot Data JPA 实现 RBAC



## Back Up
* Many To Many with Entity Graph
https://stackoverflow.com/questions/38639879/spring-data-jpa-entity-graphs-are-not-working-with-spring-boot

* JPA Best Practise
https://thorben-janssen.com/best-practices-for-many-to-many-associations-with-hibernate-and-jpa/

* JAP Sample
https://www.infoworld.com/article/3387643/java-persistence-with-jpa-and-hibernate-part-2-many-to-many-relationships.html?page=2
https://www.baeldung.com/jpa-entity-graph


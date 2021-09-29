# Helix Fish Security Authentication and Authorization Project
## UAA 系统架构
### UAA 架构图
### UAA 核心组件

#### Spring Security OAuth2.0
## UAA 系统核心功能
## UAA 开发环境搭建
1. Git
2. JDK
3. Maven
4. Intellij Idear
5. Docker 本地安全 - 建议
    * 获取 Mysql 最新版数据库Image
        ```shell script
        docker pull mysql:latest
        ```
     * 安装并配置MySQL 登录信息
         ```shell script
         docker run -itd -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 --name mysql-latest mysql
         ```
6. 安装 MySql - Docker
## UAA 系统DevSecuOps
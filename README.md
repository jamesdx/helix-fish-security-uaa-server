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
6. 安装 MySql - Docker
    * 获取 Mysql 最新版数据库Image
        ```shell script
        docker pull mysql:latest
        ```
     * 安装并配置MySQL 登录信息
         ```shell script
         docker run -itd -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 --name mysql-latest mysql
         ```
     * 修改 MySQL字符集为 UTF-8, 重启MySQL 服务器 , mysql.cnf 配置文件存放在 `/deploy/local/mysql.cnf`
         ```shell script
         #在 Mysql 客户端检查下 MySQL 的字符集，如果不是 UTF-8 ，可以用下面命令修改
         show variables like '%character%';
         docker cp ./mysql.cnf %{MYSQL_DOCKER_IMAGE_ID}:/etc/mysql/conf.d/
         ```
7. 安装 Redis - Docker
* 获取 Redis 最新版镜像
```shell script
# 下载 镜像
docker pull redis:latest
# 创建 Redis 运行及数据文件 - 注意操作用户权限。
mkdir ${YOUR_REDIS_WORKSPACE}/data
mkdir ${YOUR_REDIS_WORKSPACE}/conf
# 拷贝 Redis 配置文件， Redis 配置文件存放在 /deploy/local/redis.conf
cp deploy/local/redis.conf ${YOUR_WORKSPACE}/conf
# 启动 Redis
docker run -p 6379:6379 --name redis -v ${YOUR_REDIS_WORKSPACE}/redis/redis.conf:/etc/redis/redis.conf  -v ${YOUR_WORKSPACE}/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
# 验证 Redis 
docker ps 
docker logs ${REDIS_CONTAINER_ID}
```
* Docker Redis Run 命名说明：*
 * -p 6379:6379:把容器内的6379端口映射到宿主机6379端口
 * -v /data/redis/redis.conf:/etc/redis/redis.conf：把宿主机配置好的redis.conf放到容器内的这个位置中
 * -v /data/redis/data:/data：把redis持久化的数据在宿主机内显示，做数据备份
 redis-server /etc/redis/redis.conf：这个是关键配置，让redis不是无配置启动，而是按照这个redis.conf的配置启动
 *–appendonly yes：redis启动后数据持久化
 
## UAA 系统DevSecuOps
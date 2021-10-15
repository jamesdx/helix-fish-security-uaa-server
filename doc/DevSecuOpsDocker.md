# Docker 开发手册
## Docker 常用命令
* `docker cp`  
    用于容器与主机之间的数据拷贝
    ```shell script
    docker cp /www/runoob 96f7f14e99ab:/www/ #将容器96f7f14e99ab的/www目录拷贝到主机的/tmp目录中
    docker cp  96f7f14e99ab:/www /tmp/ #将容器96f7f14e99ab的/www目录拷贝到主机的/tmp目录中
    ```
## Docker 安装
* 安装依赖包
```shell script
sudo yum install -y yum-utils device-mapper-persistent-data lvm2 
```
* 设置阿里云镜像源
```shell script
sudo yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo 
```
* 安装 Docker-CE
    * 重建 yum 缓存
        ```shell script
        yum clean all
        yum makecache
        ```
    * 安装 Docker-CE 
        ```shell script
        sudo yum install docker-ce
        ```
      
* 配置镜像加速器
    ```shell script
    sudo mkdir -p /etc/docker
    sudo tee /etc/docker/daemon.json <<-'EOF'
    {
      "registry-mirrors": ["你的加速器地址"]
    }
    EOF
    ```

* 配置重新加载，并重启 Docker
    ```shell script
    systemctl daemon-reload
    systemctl restart docker
    ```
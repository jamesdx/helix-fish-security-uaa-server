# Docker 开发手册
## Docker 常用命令
* `docker cp`  
    用于容器与主机之间的数据拷贝
    ```shell script
    docker cp /www/runoob 96f7f14e99ab:/www/ #将容器96f7f14e99ab的/www目录拷贝到主机的/tmp目录中
    docker cp  96f7f14e99ab:/www /tmp/ #将容器96f7f14e99ab的/www目录拷贝到主机的/tmp目录中
    ```
  
* `docker build`
* `docker push`
* `docker login`
* `docker scan`   
    扫描 docker 镜像存在问题，例如：
    ```shell script
    docker scan ${image_name}:${image_version}
    ```
* `docker rmi` 删除 Docker 镜像。

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
## Docker File 
### Dokcerfile 语法 
* FROM
```shell script
FROM：定制的镜像都是基于 FROM 的镜像，这里的 nginx 就是定制需要的基础镜像。后续的操作都是基于 nginx。
```
* RUN
```shell script
RUN <命令行命令>
# <命令行命令> 等同于，在终端操作的 shell 命令。
```
* ENV
* CMD  
类似于 RUN 指令，用于运行程序，但二者运行的时间点不同:
    * CMD 在docker run 时运行。
    * RUN 是在 docker build。
作用：为启动的容器指定默认要运行的程序，程序运行结束，容器也就结束。
    ```shell script
    CMD <shell 命令> 
    CMD ["<可执行文件或命令>","<param1>","<param2>",...] 
    CMD ["<param1>","<param2>",...]  # 该写法是为 ENTRYPOINT 指令指定的程序提供默认参数
    ```
注意：如果 Dockerfile 中如果存在多个 CMD 指令，仅最后一个生效。
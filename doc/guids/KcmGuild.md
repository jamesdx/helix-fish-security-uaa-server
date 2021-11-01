#KCM 介绍
KCM K8S Cluster Management 用于自动化、定制化、离线构建 K8S 高可用弹性集群。
#KCM 操作手册
* 以 root 用户登陆
* 
# KCM 离线安装文件准备
我们采用Docker 镜像的方式来准备安装文件，也就是将安装文件打包在Docker容器中，然后在安装的主机上现在 Docker 镜像，然后运行 镜像，然后在COPY出文件，最后停止并删除容器。
构建Docker 镜像脚本存放在 `deploy/images`
# KCM Ansible 离线按照操作步骤
* 将 cicd 文件copy到/etc下  
* `配置 Ansible Inventory 配置文件`
cicd/config/config.yml 和 hosts 文件
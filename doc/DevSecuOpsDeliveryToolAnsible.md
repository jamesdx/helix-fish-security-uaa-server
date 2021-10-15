# Ansible 开发文档
## Ansible 基本概念
### Inventory  
Ansible管理的主机信息，包括IP地址、SSH端口、账号、密码等
### Module  
任务均有模块完成，也可以自定义模块，例如经常用的脚本。
### 常用模块
* `shell`   
    在目标主机执行shell命令
    ```shell script
    - name: 将命令结果输出到指定文件
      shell: somescript.sh >> somelog.txt
    - name: 切换目录执行命令
      shell:
        cmd: ls -l | grep log
        chdir: somedir/
    - name: 编写脚本
      shell: |
          if [ 0 -eq 0 ]; then
             echo yes > /tmp/result
          else
             echo no > /tmp/result
          fi
      args:
        executable: /bin/bash
    ```
  
* copy
### Plugin  
使用插件增加Ansible核心功能，自身提供了很多插件，也可以自定义插件。例如connection插件，用于连接目标主机。
### Playbook
模块化定义一系列任务，供外部统一调用。Ansible核心功能
## Ansible 常用命令
ansible-doc –l 查看所有模块
ansible-doc –s copy 查看模块文档
## CentOs 安装 Ansible
* 跟新yum源，并安装Phyon
    ```shell script
    yum update && yum install python -y
    ```
* 安装 python2 和 3.5  
注意pip 21.0以后不再支持python2和python3.5，需要如下安装
    ```shell script
    curl -O https://bootstrap.pypa.io/pip/2.7/get-pip.py
    python get-pip.py
    python -m pip install --upgrade "pip < 21.0"
    ```
* pip安装ansible(国内如果安装太慢可以直接用pip阿里云加速)
    ```shell script
    pip install ansible -i https://mirrors.aliyun.com/pypi/simple/
    
    ```
* 配置Ansible SSH 免密登陆
    * 生成公钥SSH  
        ```shell script
        # 更安全 Ed25519 算法
        ssh-keygen -t ed25519 -N '' -f ~/.ssh/id_ed25519
        # 或者传统 RSA 算法
        ssh-keygen -t rsa -b 2048 -N '' -f ~/.ssh/id_rsa
        ``` 
  * 复制密钥到需要安装的主机   
    本地主机的公钥复制到远程主机的authorized_keys文件上，ssh-copy-id命令也会给远程主机的用户主目录（home）和~/.ssh, 和~/.ssh/authorized_keys设置合适的权限
    ```shell script
     ssh-copy-id ${ALL_SERVER_IPS} #$ALL_SERVER_IPS为所有节点地址包括自身，按照提示输入yes 和root密码
    ```
## Ansible 如何实现多机自动化部署
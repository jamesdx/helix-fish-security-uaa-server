# Ansible 开发文档
## Ansible 基本概念
### Inventory  
Ansible管理的主机信息，包括IP地址、SSH端口、账号、密码等
### Module  
任务均有模块完成，也可以自定义模块，例如经常用的脚本。
### 常用模块
* `file`  
创建、修改、删除文件或者目录, file模块常用的几个参数：state、path、src、dest、mode、owner、group、name、recurse
    * state后面跟的参数： 
    * touch  ：创建文件
    * directory：创建目录
    * absent：删除文件或者目录或者链接文件
    * link：创建链接文件
    * recurse：当文件为目录时，是否进行递归设置权限  
    
    案例：  
    ```shell script
    - name: prepare some dirs
      file: name={{ item }} state=directory
      with_items:
      - "/etc/chrony"
      - "/var/lib/chrony"
      - "/var/log/chrony"
    ```
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

## Ansible 语法
* `register`  
ansible register 这个功能非常有用。当我们需要判断对执行了某个操作或者某个命令后，如何做相应的响应处理（执行其他 ansible 语句），则一般会用到register 。
* `when`
    * 关联一个文件
    如果有多个任务都需要使用同一个条件语句控制。可以将这些任务打包到一个单独的任务文件中，然后使用`include`包含和`when`条件语句。
    条件语句只对包含任务文件起作用，对包含playbook文件不起作用。指定的条件语句会作用到所包含的每个任务上.   
        ```shell script
        - include: tasks/sometasks.yml
              when: "'reticulating splines' in output"
        ```  
    * 角色中使用when  
    ```shell script
    - hosts: webservers
          roles:
             - { role: debian_stock_config, when: ansible_os_family == 'Debian' }
    ```

* `import_tasks`
import_tasks(Static)方法会在playbooks解析阶段将父task变量和子task变量全部读取并加载   

* `item` 和 `with_items`
`item`是循环变量, `with_items` 需要循环的变量。  
```shell script
- name: prepare some dirs
  file: name={{ item }} state=directory
  with_items:
  - "/etc/chrony"
  - "/var/lib/chrony"
  - "/var/log/chrony"
```
### Role
### Role 文件结构及说明
```properties
roles:          <--所有的角色必须放在roles目录下，这个目录可以自定义位置，默认的位置在/etc/ansible/roles
  project:      <---具体的角色项目名称，比如nginx、tomcat、php
    files：     <--用来存放由copy模块或script模块调用的文件。
    templates： <--用来存放jinjia2模板，template模块会自动在此目录中寻找jinjia2模板文件。
    tasks：     <--此目录应当包含一个main.yml文件，用于定义此角色的任务列表，此文件可以使用include包含其它的位于此目录的task文件。
      main.yml
    handlers：  <--此目录应当包含一个main.yml文件，用于定义此角色中触发条件时执行的动作。
      main.yml
    vars：      <--此目录应当包含一个main.yml文件，用于定义此角色用到的变量。
      main.yml
    defaults：  <--此目录应当包含一个main.yml文件，用于为当前角色设定默认变量。
      main.yml
    meta：      <--此目录应当包含一个main.yml文件，用于定义此角色的特殊设定及其依赖关系。
      main.yml
```
* `include_var` 
指定使用的变量文件，这个变量文件存放在 vars目录：
```shell script
# 设置使用变量文件，这个变量文件存放在/vars下，名字根据操作系统来。
- name: Set OS family dependent variables
  include_vars: '{{ ansible_facts.os_family }}.yml' 
  tags: always

```

### Ansible 默认变量
* ansible_facts
它是单个主机的属性，比如IP地址，操作系统，网络信息。当处理不同主机的差异时可以根据ansible_facts的值进行判断。比如：当操作系统是CentOS时，安装哪个包，怎么安装
当IP地址为内部IP时，就跳过配置防火墙当文件系统快满时，执行清理任务。 例如：
```shell script
tasks:
- name: Shut down CentOS systems
command: /sbin/shutdown -t now
when: ansible_facts['distribution'] == "CentOS"
```
## Ansible 常用命令
* ansible-playbook
    * `-i`  
    ```shell script
      -i INVENTORY, --inventory-file=INVENTORY
                 #specify inventory host path (default=/etc/ansible/hosts) or comma separated host list.
                 #指定要读取的Inventory文件
                 # 例如：ansible-playbook -i clusters/hosts -e @clusters/config.yml
    ```
ansible-doc –l 查看所有模块
ansible-doc –s copy 查看模块文档



## CentOs 安装 Ansible
* 跟新yum源，并安装Python
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
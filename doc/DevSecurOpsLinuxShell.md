# Linux Shell 开发文档

## Linux 系统基础环境编辑
* RUID  
    实际用户ID（RUID）：用于标识一个系统中用户是谁，一般是在登录之后，就被唯一确定的，就是登陆的用户的uid
* EUID  
    有效用户 ，在正常情况下，一个用户登录之后（我们假设是A用户），A用户的有效用户ID和实际用户ID是相同的
## Linux 常见文件夹
* bin目录:   
bin为binary的简写主要放置一些 系统的必备执行档例如:cat、cp、chmod df、dmesg、gzip、kill、ls、mkdir、more、mount、rm、su、tar等。
 
* /usr/bin目录:  
主要放置一些应用软件工具的必备执行档例如c++、g++、gcc、chdrv、diff、dig、du、eject、elm、free、gnome*、 zip、htpasswd、kfm、ktop、last、less、locale、m4、make、man、mcopy、ncftp、 newaliases、nslookup passwd、quota、smb*、wget等。
 
* /sbin目录:   
主要放置一些系统管理的必备程序例如:cfdisk、dhcpcd、dump、e2fsck、fdisk、halt、ifconfig、ifup、 ifdown、init、insmod、lilo、lsmod、mke2fs、modprobe、quotacheck、reboot、rmmod、 runlevel、shutdown等。
     
* /usr/sbin目录:   
放置一些网路管理的必备程序例如:dhcpd、httpd、imap、in.*d、inetd、lpd、named、netconfig、nmbd、samba、sendmail、squid、swap、tcpd、tcpdump等
 
* 综述：  
如果这是用户和管理员必备的二进制文件，就会放在/bin。如果这是系统管理员必备，但是一般用户根本不会用到的二进制文件，就会放在 /sbin。
相对而言。如果不是用户必备的二进制文件，多半会放在/usr/bin；如果不是系统管理员必备的工具，多半会放在/usr/sbin。
## 常用命令

* `ln`   
它的功能是为某一个文件在另外一个位置建立一个同不的链接，这个命令最常用的参数是-s,具体用法是：ln -s 源文件 目标文件

    ```shell script
    ln -s 源文件 目标文件。
    ```
* `cut`
-d用来定义分隔符，默认为tab键，
-f表示需要取得哪个字段
    ```shell script
    cut -d'=' -f2 #获取分隔符=后面的字段，例如： userName=Allen, 这个命令结果是 Allen
    ```

* `grep`  
    命令用于查找文件里符合条件的字符串，例如：
    ```shell script
    sed -e  是可以在一行里执行多条命令 
    ```


* `${}`  
    在bash中，$( )与` `（反引号）都是用来作命令替换的。 他可以把两个命令的结果拼接在一起。命令替换与变量替换差不多，都是用来重组命令行的，先完成引号里的命令行，然后将其结果替换出来，再重组成新的命令行。
    ```shell script
    # grep 找到结果，然后  cut 去拆分
    $(grep 'calicoVer=' ezdown|cut -d'=' -f2)
    ```
*  `set`
    * set -o nounset  
    在默认情况下，遇到不存在的变更，会忽略并继续执行，而这往往不符合预期,加入该选项,可以避免恶果扩大,终止脚本的执行。
    * set -o errexit  
    在默认情况下，遇到执行出错，会跳过并继续执行，而这往往不符合预期,加入该选项,可以避免恶果扩大,终止脚本的执行。
    * `-i`  
    -i 表示的是替换并直接修改文件, sed -i 's/被替换值/替换的值/g'
        ```shell script
        #将xxxx 替换为yyyy；在当前目录下的所有txt文件，命令为；
        sed -i 's/xxxx/yyyy/g' ./*.txt
        ```
    * `-e`
    sed -e  是可以在一行里执行多条命令 
* `readlink`  
    描述是输出符号链接值或者权威文件名，一般用于 系统中的某个命令到底是执行哪个可以执行文件。   
    * -f 选项可以递归跟随给出文件名的所有符号链接以标准化，除最后一个外所有组件必须存在。简单地说，就是一直跟随符号链接，直到直到非符号链接的文件位置，限制是最后必须存在一个非符号链接的文件。
    例如：
        ```shell script
        $ readlink -f /usr/bin/awk  
        /usr/bin/gawk 
        ```
* `Linux \033状态 `  
   命令行的背景颜色，
   格式: echo -e "\033[字背景颜色;字体颜色m字符串\033[0m" 
      
## Shell 语法
* $ 用法
    ```shell script
    $0 这个程式的执行名字
    $n 这个程式的第n个参数值，n=1..9
    $* 这个程式的所有参数,此选项参数可超过9个。
    $# 这个程式的参数个数
    $$ 这个程式的PID(脚本运行的当前进程ID号)
    $! 执行上一个背景指令的PID(后台运行的最后一个进程的进程ID号)
    $? 执行上一个指令的返回值 (显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误)
    $- 显示shell使用的当前选项，与set命令功能相同
    $@ 跟$*类似，但是可以当作数组用
    ```
* [[]] 用法   
[[ ]]是 Shell 内置关键字，它和 test 命令类似，也用来检测某个条件是否成立。  
    * [[]] 用法  
    ```shell script
    [[expression]]
    ```
    当 [[ ]] 判断 expression 成立时，退出状态为 0，否则为非 0 值。注意[[ ]]和expression之间的空格，这两个空格是必须的，否则会导致语法错误。
* -d,-e,-f 用法  
    ```shell script
    查看文件或者目录是否存在，经常用到-d,-e,-f，其中区别如下
    
    -e filename 如果 filename存在，则为真
    -d filename 如果 filename为目录，则为真
    -f filename 如果 filename为常规文件，则为真
    -L filename 如果 filename为符号链接，则为真
    -r filename 如果 filename可读，则为真
    -w filename 如果 filename可写，则为真
    -x filename 如果 filename可执行，则为真
    -s filename 如果文件长度不为0，则为真
    -h filename 如果文件是软链接，则为真
    ```
* apk - Alpine Linux  
    alpine 提供了非常好用的apk软件包管理工具，通过apk –help命令查看完整的包管理命令。
    * apk add
      add：安装PACKAGES并自动解决依赖关系。   
      add命令从仓库中安装最新软件包，并自动安装必须的依赖包，也可以从第三方仓库添加软件包。  
        ```shell script
        $ apk add openssh openntp vim
        $ apk add --no-cache mysql-client
        $ apk add docker --update-cache --repository http://mirrors.ustc.edu.cn/alpine/v3.4/main/ --allow-untrusted
        ```
## Resources



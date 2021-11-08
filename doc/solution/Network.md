#需求
#方案

#背景
## 网络设备
### 交换机
交换机是根据MAC地址转发数据帧。在交换机中有一张表，这个表记录了局域网主机MAC地址对应的交换机接口对应关系，
交换机根据这个对应关系把数据数据帧传输到指定的主机上。 交换机工作在二层。
### 集线器

### 路由器
路由器工作在三层网络，

 
## NAT Network Address Translation
NAT 是处理 IPv4准许一个整体机构以一个公用IP地址出现在 Internet 上。它是一个把内部网络地址翻译成合法网络IP地址技术。
## Overlay - 模拟网络 Underlay - 物理网络
## Overlay 
Overlay 是一种网络架构上叠加的虚拟技术模式，它不会对网络镜像大规模修改的条件，并且基于IP的基础网络技术。
### Underlay
## 网络模型
### TCP/IP

* 二层 网络 - 
* 三层 传输

### TCP
### UDP

##OSI 参考模型
### 二层 网络
* Mac 地址学习
* ARP 广播 


## 五层协议体系

### 协议工作原理
* 数据报文封装 是从上层到下层（应用层，传输层，网络层，数据链路层，物理层），解封是 从底层到上层。
* 二次内用MAC地址选址，三层内通过IP寻址；
* MAC 地址不多变化，但是 IP地址不变（除 NAT ） 
### 一个完整 HTTP 请求流程

## GRE
## VLAN
## 网络隧道 Tunneling Protocol
对现有网络最小改动的情况下，扩展现有网络。

`Virtual Local Area Network` 
Generic Routing Encapsulation 通用路由封装

## NVO3 Network Virtualization Over Layer 3
NVO3 是基于二层的 3 层 Overlay 网络，主要 NvGRE,STT 和 VXLAN。 因此 NVO3 是为虚拟机或者容器服务的。

### NvGRE - MAC In GRE
### STT - MAC In TCP
### VXLAN Mac in UDP
VXLAN 是一种`虚拟隧道协议`技术，通过 TCP/IP的 `三层` 网络搭建虚拟的 `二层` 网络，VXLAN 是基于 `UDP` 模拟构建的 Overlay （覆盖网络技术），因此 VXLAN 是在操作`系统内核`中实现 `再封包` 。
那么VALAN 的再封包的流程是，Vxlan在内核当中实现，当数据包使用vxlan设备发送数据时，会打上vlxan的头部信息，在发送出去，对端解包，并根绝VNI把原是报文发送到目的服务器。
VXLAN是一个逻辑上的网络层， 它使用逻辑关系建立2个不同虚拟机的通讯，但是真是还是需要二层网络去传输的。VXALN 为 每个 VETP 分配一个物理IP，

#### 基本组件及概念
* NVE Network Virtual Edge
NVE 设备运行在 Overlay 网络边界，实现二层（IP）、三层（传输）的虚拟功能。
* VTEP - VXLAN Tunnel Endpoints
VXLAN 网络边缘设备，用来进行VXLAN的报文处理（封包和解包）。 VTEP 可以是网络设备（比如交换机），也可以是一台主机（虚拟化集群中的宿主机）。

* VNI - VXALN Network Identifier - VXLAN 网络标识 - 24 比特
VXLAN 网络标识 ，是一个24 位整数，一共是2^24= 16777216 , 在VXLAN搭建的云上，一般每个VNI对应一个租户。
* Tunnel - VXLAN 隧道
Tunnel是个逻辑上的概念，在VXLAN模型中并没有具体物理设备，Tunnel可以理解一个虚拟通道，VXLAN通信双方认为自己在直接通信，并不知道底层的网络的存在。
可以每个VXLAN为通讯的虚拟机搭建了一个独立通信通道，这就是 Tunnel。

* VXLAN 报文
### 为什么使用 VXLAN 
* VLAN ID 数量闲置
* 交换机  MAC 地址表闲置
* 虚拟机或容器迁移范围限制 
### 原理及工作流程
再封装技术，基于新的头部实现管道互联
* 基于 管道 的思路。 - 再封装技术
* UPD 模式
* 底层还是基于IP层。
### VXLAN 工作流程?Image

# K8S 网络
## K8S 依赖物理环境通讯
### Overlay - VXLAN
### Underlay - GRT - Rooter
## K8S POD 通信方式
### 同一个 POD 下 容器间通信 - 横向本地
通过共享 Pause 容器的网络空间，来实现POD下容器间的通讯。
Pause 容器，从网络的角度，同一个Pod下的不同容器犹如运行在一个专有的主机上，可以通过Localhost进行通讯。
####  Pause 容器作用
* 共享网络空间
* PID Namespace 共享，收集POD中僵尸进程；
#### Pause 容器原理
* 从 Namespace 角度理解 Pause 容器
在 Linux 系统中，运行的新进程时，该进程从父进程继承来其 namespace. 在 Namespace 中运行进程的方法，这样就取消与父进程共享 namespace , 也就创建了一个新的 Namespace
,可以通过将其他进程加入到这个进程来共享 Namespace,进而实现来一个 POD 。
* 从 PID 角度理解 Pause 容器
Pause 容器进程 是 Pod 所有容器的父进程。

### POD 内 容器的通信
通过 Pause 容器共享网络空间。

### POD 间通行 - 横向K8S
#### 同一个 NODE 间POD通信  
LXC 的 虚拟网络 提供了 同一个Node 中 POD网络通讯  

##### 同一个NODE，POD间通信原理
每个POD有自己的网络命名空间，我们使用 Linux 虚拟以太网设备把多个POD的网络命名空间连接到Root空间，并且使用 网桥 Bridge 让多个POD之间通讯。
* `veth对` 将 POD 的网络空间 与 Root（宿主机） 网络空间链接起来  
`Linux 虚拟以太网设备`，它由两个`虚拟接口`组成的`veth对`使得不同的网络空间链接起来。 这些虚拟接口分布在多个网络命名空间上（Pod）.
为了让多个 Pod 的网络空间链接起来，Linux 让 veth对 的一端连接到 Root 的网络空间，另一端链接到Pod的网路空间。
* 网桥 Bridge 设备，多个链接 Root 网络空间的 POD进行通讯。
Linux Ethernet Bridge 是一个虚拟的二层网络设备， 网桥实现类 ARP协议。
##### 同一个NODE同步POD通信流程：?Image
#### 不同NODE上 POD 间通讯
##### VXLAN - Overlay
###### Flannel
Flannel 致力于给 K8S 集群中的 Node 提供一个3层网络，它并不控制Node中的容器的网络，仅关注流量如何在 Node 之间传递。
* Flannel 工作原理   
Flannel 构建了一个 VXLAN的 覆盖网络。 首先 Flannel 创建另一个 新的 `虚拟网卡 flannel0`，Flannel0 是一个VXLAN设备， 它接收 `Docker01 网桥` 数据；然后根据一个`路由表` 封包或者转发 ， 这个路由表存放在 `etcd` 中。Flannel 同时也会创建一个 Flannel 代理，
这个代理，负责给当前 NODE 申请一个 CIDR 地址块，用来给这个NODE上的POD分配IP。
* Flannel 流程 - 
跨NODE POD 通信流程?Image
Node 1 上的 POD1(10.1.15.2) 与 Node 上的 POD2 (10.1.20.5) 进行通信。  
* Step 1: Pod1 通过 veth 把数据发到 Node1 的 Docker0(10.1.15.1/24) 网桥，Docker01 发现请求目的IP 10.1.20.5 不是 自己管理的网段，此时会把数据转发默认路由 Flannel0(VXLAN设备)。
* Step 2：在 Flannel0 接收数据后，Flannel0 封装二层数据包，此时需要目标的MAC地址，Flannel0 会通过 Linux 内核调用 Flanned （在用户空间的程序）获取，Flanned 首先会尝试从 ETCD 超找匹配的
         的子网VTEP信息，也就是找到 Node2 上的 flannel0 的 MAC 地址。 最后 Flanned 会把 查询出来的信息放入到 Node 主机的 arp cache表中；
* Step 3: Linux 内核会根据 arp cache表找到 10.1.20.5 对应的 MAC 地址，然后将其封装层二层协议中的目标 MAC地址。



##### Underlay - 路由方式
 
#### Service 网络 - 横向抽象 Service 层
### POD 与外网节点通讯 - 纵向跨层通讯

#### 外部接入 - Node Port/LoadBalancer/Ingress

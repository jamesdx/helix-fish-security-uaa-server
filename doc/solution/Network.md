#需求
#方案

#背景
## Network 
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
*  Mac 地址学习
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

### POD 间通行 - 横向K8S
#### 同一个 NODE 间POD通信 
#### 不同NODE上 POD 间通讯
#### Service 网络 - 横向抽象 Service 层
### POD 与外网节点通讯 - 纵向跨层通讯

#### 外部接入 - Node Port/LoadBalancer/Ingress

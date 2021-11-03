#需求
#方案

#背景
# 基础概念
## Overlay - 模拟网络 Underlay - 物理网络
### Overlay 
Overlay 是一种网络架构上叠加的虚拟技术模式，它不会对网络镜像大规模修改的条件，并且基于IP的基础网络技术。
### Underlay
# 网络模型
## TCP/IP

## 三层 网络 - 


## 四层 传输
### TCP
### UDP
## OSI 参看模型
### 二层 网络
#### Mac 地址学习
#### ARP 广播

## 五层协议体系



## GRE
## VLAN
## 网络隧道 Tunneling Protocol
对现有网络最小改动的情况下，扩展现有网络。

`Virtual Local Area Network` 
Generic Routing Encapsulation 通用路由封装

# NVO3 Network Virtualization Over Layer 3
NVO3 是基于二层的 3 层 Overlay 网络，主要 NvGRE,STT 和 VXLAN。 因此 NVO3 是为虚拟机或者容器服务的。

## NvGRE - MAC In GRE
## STT - MAC In TCP
## VXLAN Mac in UDP
VXLAN 是一种`虚拟隧道协议`技术，通过 TCP/IP的 `三层` 网络搭建虚拟的 `二层` 网络，VXLAN 是基于 `UDP` 模拟构建的 Overlay （覆盖网络技术），因此 VXLAN 是在操作`系统内核`中实现 `再封包` 。
那么VALAN 的再封包的流程是，Vxlan在内核当中实现，当数据包使用vxlan设备发送数据时，会打上vlxan的头部信息，在发送出去，对端解包，并根绝VNI把原是报文发送到目的服务器。

### 基本组件及概念
####NVE Network Virtual Edge
NVE 设备运行在 Overlay 网络边界，实现二层（IP）、三层（传输）的虚拟功能。
#### VTEP - VXLAN Tunnel Endpoints
VXLAN 网络边缘设备，用来进行VXLAN的报文处理（封包和解包）。 VTEP 可以是网络设备（比如交换机），也可以是一台主机（虚拟化集群中的宿主机）。

#### VNI - VXALN Network Identifier - VXLAN 网络标识 - 24 比特
VXLAN 网络标识 ，是一个24 位整数，一共是2^24= 16777216 , 在VXLAN搭建的云上，一般每个VNI对应一个租户。
#### Tunnel - VXLAN 隧道
Tunnel是个逻辑上的概念，在VXLAN模型中并没有具体物理设备，Tunnel可以理解一个虚拟通道，VXLAN通信双方认为自己在直接通信，并不知道底层的网络的存在。
可以每个VXLAN为通讯的虚拟机搭建了一个独立通信通道，这就是 Tunnel。

#### VXLAN 报文
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

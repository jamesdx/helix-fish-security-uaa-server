#需求
#方案

#背景
# TCP/IP
## OIS 参看模型
## 二层 网络
### Mac 地址学你
### ARP 广播
## 三层 网络 - 

## 四层 传输
### TCP
### UDP

## GRE
## VLAN
## 网络隧道 Tunneling Protocol
对现有网络最小改动的情况下，扩展现有网络。

`Virtual Local Area Network` 
Generic Routing Encapsulation 通用路由封装

# NVO3 Network Virtualization Over Layer 3
## 
## VXLAN Mac in UDP
VXLAN 是一种`虚拟隧道协议`技术，通过`三层`网络搭建虚拟的`二层`网络，VXLAN 是基于 `UDP`，因此 VXLAN 是再操作`系统内核`中实现再 `封包` 。
那么VALAN 的再封包的流程是，Vxlan在内核当中实现，当数据包使用vxlan设备发送数据时，会打上vlxan的头部信息，在发送出去，对端解包，并根绝VNI把原是报文发送到目的服务器。

### 为什么使用 VXLAN  
虚拟化技术
### 原理
再封装技术，基于新的头部实现管道互联
* 基于 管道 的思路。 - 再封装技术
* UPD 模式
### 基本组件
#### VTEP
#### VNI - 24 比特






# K8S 网络

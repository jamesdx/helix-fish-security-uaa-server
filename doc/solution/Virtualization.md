#需求
#方案
#资源
## Docker 原理
Docker 本质是宿主机机器上的进程。Docker通过Namespace 是现在资源的隔离，通过 cgroup 实现了资源的限制，通过写时复制机制实现高效文件操作。
## Linux 命名空间
## CGroup
虚拟机和容器都一样都是通过 CGroup 做资源配额，而原理上都是抽象一个隔离的运行环境。
## 写事复制
## LXC Linux Container
### Linux Namespace
### GCGroup


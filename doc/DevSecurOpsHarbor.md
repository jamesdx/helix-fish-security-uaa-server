# Harbor 安装 - 离线方式
## Harbor 安装几种方式
### 基于 K8S Cluster 安装 
### 本地物理安装
### Docker 安装
* 修改 centos hosts 
    ```shell script
    140.82.113.4 github.com 
    199.232.69.194  github.global.ssl.fastly.net
    185.199.108.153  assets-cdn.github.com
    185.199.109.153  assets-cdn.github.com
    185.199.110.153  assets-cdn.github.com
    185.199.111.153  assets-cdn.github.com
    
    ```
* 下载 Harbor 离线安装包
    到 Harbor 发布网站下载 Harbor 离线安装包
    https://github.com/goharbor/harbor/releases
    ```shell script
    wget --continue https://github.com/goharbor/harbor/releases/download/v2.3.3/harbor-offline-installer-v2.3.3.tgz
    ```



* 解压 Harbor 离线安全文件
    ```shell script
    tar -xzvf harbor-offline-installer-v2.3.3.tgz
    ```
* 导入离线安装包中 Harbor Docker Images
```shell script
cd harbor
docker load -i harbor.v2.3.3.tar.gz 
```

* 在环节变量，添加 Harbor Server IP
```shell script
export NODE_IP=192.168.67.32 # 当前部署 harbor 的节点 IP
```
* 创建 harbor nginx 服务器使用的 x509 证书
创建 harbor 证书签名请求：
```shell script
cat > harbor-csr.json <<EOF
{
  "CN": "harbor",
  "hosts": [
    "127.0.0.1",
    "${NODE_IP}"
  ],
  "key": {
    "algo": "rsa",
    "size": 2048
  },
  "names": [
    {
      "C": "CN",
      "ST": "BeiJing",
      "L": "BeiJing",
      "O": "k8s",
      "OU": "opsnull"
    }
  ]
}
EOF
```

```shell script
mkdir -p /data/cert && cd /data/cert

```
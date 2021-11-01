#!/bin/bash
export KCM_VER=1.0.1
export K8S_BIN_VER=v1.18.3
export EXT_BIN_VER=0.5.2

(
  cd dockerfiles/kcm
  docker build -t kcm:${KCM_VER} .
  docker tag kcm:${KCM_VER} registry.cn-beijing.aliyuncs.com/helix_platform/helix/kcm:${KCM_VER}
)

cat password.txt | docker login -u wz@1027347628499132.onaliyun.com registry.cn-beijing.aliyuncs.com --password-stdin
docker push registry.cn-beijing.aliyuncs.com/helix_platform/helix/kcm:${KCM_VER}


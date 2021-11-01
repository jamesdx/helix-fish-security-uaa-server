#!/bin/sh
export KCM_VER=1.0.2
export K8S_BIN_VER=v1.18.3
export EXT_BIN_VER=0.5.2
export KCM_IMAGE_REPOSITORY_PASSWORD_FILE=password.txt
export KCM_IMAGE_REPOSITORY_USERNAME=allenzhe1987
export KCM_IMAGE_SERVER_URL=registry.cn-beijing.aliyuncs.com
export KCM_IMAGE_REPOSITORY_URL=registry.cn-beijing.aliyuncs.com/helix_platform/helix

function buildKcmBasicDockerImages() {
  KCM_IMAGE_ID=$(docker images | grep kcm | awk '{print $3}')
  echo
  if [ ! -n "$KCM_IMAGE_ID" ]; then echo "IS NULL"
    else docker rmi $KCM_IMAGE_ID;

  fi

  docker build -t kcm:${KCM_VER} ./dockerfiles/kcm
  KCM_IMAGE_ID=$(docker images | grep kcm | awk '{print $3}')
  cat ${KCM_IMAGE_REPOSITORY_PASSWORD_FILE} | docker login -u ${KCM_IMAGE_REPOSITORY_USERNAME} ${KCM_IMAGE_SERVER_URL} --password-stdin
  docker tag $KCM_IMAGE_ID ${KCM_IMAGE_REPOSITORY_URL}:${KCM_VER}
  docker push ${KCM_IMAGE_REPOSITORY_URL}:${KCM_VER}

}
function main() {
    echo "run....";
    buildKcmBasicDockerImages
}

main "$@"

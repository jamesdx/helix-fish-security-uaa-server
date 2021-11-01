#安装 Python,Ansible, 同时下载KCF 安装程序，例如：Ansible 脚本，安装集群配置文件等。
# 前提条件：KCF 安装程序已经服务。
FROM alpine:3.11.12

ENV KCM_VER=1.0.1\
    ANSIBLE_VER=2.6.18 \
    NETADDR_VER=0.7.19
RUN echo "https://mirrors.aliyun.com/alpine/v3.9/main" > /etc/apk/repositories && \
    echo "https://mirrors.aliyun.com/alpine/v3.9/community" >> /etc/apk/repositories && \
    echo "===> Adding Python runtime..."                                    && \
    apk --update add python py-pip openssl ca-certificates                  && \
    apk --update add --virtual build-dependencies \
        python-dev libffi-dev openssl-dev build-base                        && \
        pip install -i https://mirrors.aliyun.com/pypi/simple --upgrade pip cffi                                          && \
        \

    echo "===> Installing Ansible/netaddr..."                               && \
    pip install -i https://mirrors.aliyun.com/pypi/simple ansible=="$ANSIBLE_VER" netaddr=="$NETADDR_VER"             && \
    \
    echo "===> Installing handy tools..."                                   && \
    pip install -i https://mirrors.aliyun.com/pypi/simple --upgrade pycrypto                                          && \
    apk --update add bash openssh-client rsync                              && \
    \

    echo "===> Downloading KCM..."                                      && \
    wget https://github.com/jamesdx/helix-fish-security-uaa-server/archive/refs/tags/"${KCM_VER}".tar.gz   && \
    tar zxf ./"$KCM_VER".tar.gz                                         && \
    mv helix-fish-security-uaa-server-"$KCM_VER" /etc/ansible                                  && \
    # ln -s /etc/ansible/helix-fish-security-uaa/cicd/kcm /usr/bin/kcm                       && \
    \

    echo "===> Removing package list..."                                    && \
    apk del build-dependencies                                              && \
    rm -rf /var/cache/apk/*                                                 && \
    rm -rf /root/.cache                                                     && \
    rm -rf ./"$KCM_VER".tar.gz
CMD [ "sleep", "360000000" ]
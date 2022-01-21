#
# SPDX-License-Identifier: MIT
#
FROM ubuntu:20.04

ARG timezone=Europe/Moscow

ENV TZ=${timezone}
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Install basic OE requirements
RUN apt-get update && apt-get install --no-install-recommends -y \
    gawk wget git-core diffstat unzip texinfo gcc-multilib \
    build-essential chrpath socat cpio python python3 python3-pip python3-pexpect \
    xz-utils debianutils iputils-ping locales openssh-client file tmux

# Installing kas
RUN pip3 install kas==2.6.3

# Change dash to bash
RUN rm /bin/sh && ln -s bash /bin/sh

# Configure locale
RUN locale-gen en_US.UTF-8 && update-locale LC_ALL=en_US.UTF-8 \
    LANG=en_US.UTF-8

ENV LANG en_US.UTF-8
ENV LC_ALL en_US.UTF-8

# Configure UID, GID, user name and project name
ARG host_uid=1000
ARG host_gid=1000
ARG username=build
ARG project=tanowrt

ENV USERNAME ${username}
ENV PROJECT ${project}

# Configure user
RUN groupadd -g $host_gid $USERNAME && \
    useradd -g $host_gid -m -s /bin/bash -u $host_uid $USERNAME

USER $USERNAME
WORKDIR /home/${USERNAME}

CMD ["/bin/bash", "--login"]

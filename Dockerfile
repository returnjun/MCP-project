# 基础镜像，可以先执行 docker pull openjdk:17-jdk-slim
FROM registry.cn-hangzhou.aliyuncs.com/xfg-studio/openjdk:17-jdk-slim

# 作者
MAINTAINER daohaha

# 配置
ENV PARAMS=""

# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 添加应用
ADD target/mcp-server-weixin.jar /mcp-server-weixin.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /mcp-server-weixin.jar $PARAMS"]
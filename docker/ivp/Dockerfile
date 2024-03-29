FROM ubuntu:20.04   as   build

ARG gitUrl="https://github.com/RongLiXu"
ARG zlmGitUrl="https://gitee.com/xia-chu/ZLMediaKit"

RUN export DEBIAN_FRONTEND=noninteractive &&\
        apt-get update && \
        apt-get install -y --no-install-recommends openjdk-11-jre git maven nodejs npm build-essential \
        cmake ca-certificates openssl ffmpeg &&\
        mkdir -p /opt/ivp/config /opt/ivp/heapdump /opt/ivp/config /opt/assist/config /opt/assist/heapdump /opt/media/www/record

RUN cd /home && \
        git clone "https://gitee.com/pan648540858/maven.git" && \
        cp maven/settings.xml /usr/share/maven/conf/

RUN cd /home && \
        git clone "${gitUrl}/ivp-pro.git"
RUN cd /home/ivp-pro/web_src && \
        npm install && \
        npm run build
RUN cd /home/ivp-pro && \
        mvn clean package -Dmaven.test.skip=true && \
        cp /home/ivp-pro/target/*.jar /opt/ivp/ && \
        cp /home/ivp-pro/src/main/resources/application-docker.yml /opt/ivp/config/application.yml

RUN cd /home && \
		git clone "${gitUrl}/ivp-pro-assist.git"
RUN cd /home/ivp-pro-assist && \
	# git reset --hard 58f1a79136a55a7cd1593c95b56ddadcc2225b61 && \
        mvn clean package -Dmaven.test.skip=true && \
        cp /home/ivp-pro-assist/target/*.jar /opt/assist/ && \
        cp /home/ivp-pro-assist/src/main/resources/application-dev.yml /opt/assist/config/application.yml

RUN cd /home && \
        git clone --depth=1 "${zlmGitUrl}"
RUN cd /home/ZLMediaKit && \
        git submodule update --init --recursive && \
        mkdir -p build release/linux/Release/ &&\
        cd build && \
        cmake -DCMAKE_BUILD_TYPE=Release .. && \
        make -j4 && \
        rm -rf ../release/linux/Release/config.ini && \
        cp -r ../release/linux/Release/* /opt/media

RUN cd /opt/ivp && \
        echo '#!/bin/bash' > run.sh && \
        echo 'echo ${IVP_IP}' >> run.sh && \
        echo 'echo ${IVP_CONFIG}' >> run.sh && \
        echo 'cd /opt/assist' >> run.sh && \
        echo 'nohup java ${ASSIST_JVM_CONFIG} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/assist/heapdump/ -jar *.jar --spring.config.location=/opt/assist/config/application.yml --userSettings.record=/opt/media/www/record/  --media.record-assist-port=18081 ${ASSIST_CONFIG} &' >> run.sh && \
        echo 'nohup /opt/media/MediaServer -d -m 3 &' >> run.sh && \
        echo 'cd /opt/ivp' >> run.sh && \
        echo 'java ${IVP_JVM_CONFIG} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/ivp/heapdump/ -jar *.jar --spring.config.location=/opt/ivp/config/application.yml --media.record-assist-port=18081 ${IVP_CONFIG}' >> run.sh && \
        chmod +x run.sh

FROM ubuntu:20.04

EXPOSE 18080/tcp
EXPOSE 5060/tcp
EXPOSE 5060/udp
EXPOSE 6379/tcp
EXPOSE 18081/tcp
EXPOSE 80/tcp
EXPOSE 1935/tcp
EXPOSE 554/tcp
EXPOSE 554/udp
EXPOSE 30000-30500/tcp
EXPOSE 30000-30500/udp

ENV LC_ALL zh_CN.UTF-8

RUN export DEBIAN_FRONTEND=noninteractive &&\
        apt-get update && \
        apt-get install -y --no-install-recommends openjdk-11-jre ca-certificates ffmpeg language-pack-zh-hans && \
        apt-get autoremove -y && \
        apt-get clean -y && \
        rm -rf /var/lib/apt/lists/*dic

COPY --from=build /opt /opt
WORKDIR /opt/ivp
CMD ["sh", "run.sh"]

FROM digitalgenius/alpine-jdk:latest
ADD *.jar /opt/lib/eurikaServer.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/eurikaServer.jar"]
VOLUME /var/lib/config-repo
EXPOSE 8761

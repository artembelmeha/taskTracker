FROM digitalgenius/alpine-jdk:latest
ADD *.jar /opt/lib/zuulServer.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/zuulServer.jar"]
VOLUME /var/lib/config-repo
EXPOSE 9000

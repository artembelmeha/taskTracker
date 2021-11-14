FROM digitalgenius/alpine-jdk:latest
ADD *.jar /opt/lib/usersService.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/usersService.jar"]
VOLUME /var/lib/config-repo
EXPOSE 8001


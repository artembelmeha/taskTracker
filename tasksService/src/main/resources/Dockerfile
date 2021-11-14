FROM digitalgenius/alpine-jdk:latest
ADD *.jar /opt/lib/tasksService.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/tasksService.jar"]
VOLUME /var/lib/config-repo
EXPOSE 8000

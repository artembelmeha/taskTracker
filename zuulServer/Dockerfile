FROM java:8-jre-alpine
ADD ./zuulServer/target/zuulServer-0.0.1-SNAPSHOT.jar zuulServer.jar
EXPOSE 9010
ENTRYPOINT ["java", "-jar", "/zuulServer.jar"]

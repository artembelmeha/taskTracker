FROM java:8-jre-alpine
ADD .zuulServer/target/zuulServer-0.0.1-SNAPSHOT.jar /opt/lib/zuulServer.jar
ENTRYPOINT ["java", "-jar", "/opt/lib/zuulServer.jar"]
EXPOSE 9000

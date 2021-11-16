FROM java:8-jre-alpine
ADD ./eurekaServer/target/eurekaServer-0.0.1-SNAPSHOT.jar /opt/lib/eurekaServer.jar
ENTRYPOINT ["java", "-jar", "/opt/lib/eurekaServer.jar"]
EXPOSE 8761

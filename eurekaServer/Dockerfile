FROM java:8-jre-alpine
ADD ./eurekaServer/target/eurekaServer-0.0.1-SNAPSHOT.jar eurekaServer.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/eurekaServer.jar"]

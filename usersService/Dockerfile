FROM java:8-jre-alpine
ADD ./usersService/target/usersService-0.0.1-SNAPSHOT.jar /opt/lib/usersService.jar
ENTRYPOINT ["java", "-jar", "/opt/lib/usersService.jar"]
EXPOSE 8001

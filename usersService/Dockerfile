FROM java:11-jre-alpine
ADD ./usersService/target/usersService-0.0.1-SNAPSHOT.jar usersService.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "/usersService.jar"]

FROM java:11-jre-alpine
ADD ./tasksService/target/tasksService-0.0.1-SNAPSHOT.jar /opt/lib/tasksService.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/opt/lib/tasksService.jar"]

FROM java:8-jre-alpine
ADD .tasksService/target/tasksService-0.0.1-SNAPSHOT.jar /opt/lib/tasksService.jar
ENTRYPOINT ["java", "-jar", "/opt/lib/tasksService.jar"]
EXPOSE 8000

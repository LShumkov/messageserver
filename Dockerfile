FROM openjdk:8-jdk-alpine
COPY /src/docker/*.jar messaging.jar
ENTRYPOINT ["java","-jar","/messaging.jar"]
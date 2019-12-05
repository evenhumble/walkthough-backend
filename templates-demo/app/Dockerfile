FROM openjdk:8-jdk-alpine

LABEL maintainer="patrick"

VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/app.jar
ADD ${JAR_FILE} application.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/application.jar"]
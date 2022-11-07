FROM openjdk:17-ea-jdk-slim
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "--enable-preview", "-jar","/app.jar" ]
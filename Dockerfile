FROM openjdk:18-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/jukeapi-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


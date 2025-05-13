### STAGE 1: Build ###
FROM maven:3.9.8-eclipse-temurin-21 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
RUN mvn dependency:go-offline
COPY src /workspace/src
RUN mvn -f pom.xml clean package

### STAGE 2: Run ###
FROM openjdk:21
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]

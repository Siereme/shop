FROM maven:3.8.5-jdk-11-slim as build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package

FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /app
COPY --from=build /project/target/*.jar /app/application.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "application.jar"]
FROM maven:3.8.4-openjdk-17 AS build
COPY pom.xml /build/
WORKDIR /build
RUN mvn dependency:go-offline

COPY src /build/src/

RUN mvn package

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /build/target/TakeUrTableApp-0.0.1-SNAPSHOT.jar /app/TakeUrTableApp.jar

EXPOSE 8080

CMD ["java", "-jar", "TakeUrTableApp.jar"]

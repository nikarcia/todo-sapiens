FROM maven:3.8.3-jdk-8-slim AS builder
WORKDIR /app
COPY ./pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn package -DskipTests

FROM openjdk:8-jdk-slim
COPY --from=builder /app/target/crud-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

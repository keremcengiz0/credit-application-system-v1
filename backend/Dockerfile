#MAVEN BUILD
FROM maven:3.8.1-openjdk-11-slim as builder
COPY pom.xml /app/
COPY src /app/src
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package -DskipTests

#RUN
FROM openjdk:11-jre-slim
WORKDIR /app
COPY  --from=builder /app/target/credit-application-system-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
FROM gradle:8.5-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test --no-daemon

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from-build /app/build/libs/*.jar auth-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "jar", "auth-service.jar"]
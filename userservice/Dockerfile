# Use a base image with Java 21
FROM openjdk:21-jdk-slim AS build

# Set working directory in container
WORKDIR /app

# Copy gradle wrapper and dependencies
COPY gradlew gradlew.bat ./
COPY gradle gradle/
COPY build.gradle.kts settings.gradle.kts ./
RUN ./gradlew build --no-daemon

# Copy the compiled jar into a new image
FROM openjdk:21-jdk-slim
COPY --from=build /app/build/libs/user-service-0.0.1-SNAPSHOT.jar user-service.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/user-service.jar"]

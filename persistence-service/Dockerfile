# Stage 1: Build the application
FROM openjdk:21-jdk-slim AS build

WORKDIR /app

# Copy gradle wrapper and config files
COPY gradlew gradlew.bat ./
COPY gradle gradle/
COPY build.gradle.kts ./

# Copy source files
COPY src src/

# Build the jar
RUN ./gradlew bootJar --no-daemon

# Stage 2: Use a lightweight image to run the app
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the JAR built in stage 1
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]

# Use the official Gradle image to build the project
FROM gradle:7.2.0-jdk11 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy only the necessary files to the container
COPY build.gradle.kts /app/
COPY src /app/src

# Build the Kotlin project with Gradle
RUN gradle installDist
RUN gradle clean build

# Use a smaller runtime image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled application from the builder stage
COPY --from=builder /app/build/libs/app.jar /app

# Specify the command to run when the container starts
CMD ["java", "-jar", "app.jar"]

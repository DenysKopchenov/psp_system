# Use an OpenJDK 17 base image
FROM openjdk:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (cache layer)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the project
RUN ./mvnw clean package

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/psp-0.0.1-SNAPSHOT.jar"]
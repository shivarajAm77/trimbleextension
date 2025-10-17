# ---- Build Stage ----
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Package the Spring Boot app (skip tests for faster build)
RUN mvn clean package -DskipTests

# ---- Run Stage ----
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the packaged jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 7088

# Set environment variables (optional)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --server.port=7088"]

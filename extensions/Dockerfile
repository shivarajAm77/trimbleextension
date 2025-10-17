# =========================================================
# 1️⃣ Build Stage - Use Maven with Java 21
# =========================================================
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory inside the image
WORKDIR /app

# Copy Maven descriptor (with full relative path)
COPY ./extension/pom.xml ./pom.xml

# Copy source code (all Java + resources)
COPY ./extension/src ./src

# Build the Spring Boot application (skip tests for speed)
RUN mvn clean package -DskipTests

# =========================================================
# 2️⃣ Runtime Stage - Use lightweight JDK image
# =========================================================
FROM eclipse-temurin:21-jdk-jammy

# Set working directory for runtime container
WORKDIR /app

# Copy the generated JAR from the build stage
COPY --from=build /app/target/*.jar ./app.jar

# Expose application port
EXPOSE 7088

# Optional memory settings (adjust as needed)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Run the Spring Boot application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar ./app.jar --server.port=7088"]

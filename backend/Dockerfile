# backend/Dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN ./mvnw clean package -DskipTests   # if Maven wrapper is included
EXPOSE 8080
CMD ["java", "-jar", "target/backend.jar"]

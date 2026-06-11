# Build stage 1: Compile Angular frontend
FROM node:22-alpine AS frontend
WORKDIR /app
COPY frontend/package*.json ./
RUN npm ci --ignore-scripts
COPY frontend/ ./
RUN npm run build -- --output-path=/app/dist

# Build stage 2: Compile Spring Boot backend and embed frontend
FROM maven:3.9-eclipse-temurin-21-alpine AS backend
WORKDIR /app
COPY backend/pom.xml .
RUN mvn dependency:go-offline -B
COPY backend/src ./src
COPY --from=frontend /app/dist/frontend/browser ./src/main/resources/static
RUN mvn package -DskipTests -B

# Runtime stage: Minimal JRE image
FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser
WORKDIR /app
COPY --from=backend /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
RUN ls -la target/
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/scm2.0-0.0.1-SNAPSHOT.jar"]
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn ./.mvn
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/scm2.0-0.0.1-SNAPSHOT.jar"]

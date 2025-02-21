FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn ./.mvn
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN ls -la target/
RUN mv target/scm2.0-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
RUN find target/ -name '*.jar' -exec cp {} app.jar \;
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
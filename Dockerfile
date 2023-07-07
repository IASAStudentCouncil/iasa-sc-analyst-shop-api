FROM openjdk:17-alpine
EXPOSE 8080
WORKDIR /app
COPY target/Backend-0.0.1.jar /app
CMD ["java", "-jar", "/app/Backend-0.0.1.jar"]
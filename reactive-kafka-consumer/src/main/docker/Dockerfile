FROM openjdk:11-jdk-slim

EXPOSE 8081
EXPOSE 5005

ENV KAFKA_HOST kafka-server
ENV KAFKA_PORT 29092

COPY reactive-kafka-consumer-1.0.jar /app.jar

CMD ["/usr/local/openjdk-11/bin/java", "-jar", "-Dspring.profiles.active=docker", "/app.jar"]



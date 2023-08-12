FROM openjdk:17-jdk

RUN mkdir /app/

WORKDIR /app/

COPY build/libs/crud-spring-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "/app/crud-spring-0.0.1-SNAPSHOT.jar"]
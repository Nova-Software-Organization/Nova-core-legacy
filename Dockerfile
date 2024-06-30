# Estágio de compilação
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /build

COPY pom.xml .
COPY src src

RUN mvn package

# Estágio de execução
FROM openjdk:17-jre

WORKDIR /app

COPY --from=build /build/target/*.jar apibackend-0.0.1-SNAPSHOT

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "apibackend-0.0.1-SNAPSHOT"]

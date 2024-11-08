FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . /app

RUN mvn package -DskipTests

FROM  eclipse-temurin:21-jre-alpine
COPY --from=builder /app/target/GAI_Owners*.jar /GAI_Owners.jar

EXPOSE 8081
ENTRYPOINT ["java", "-XX:+UseContainerSupport","-XX:MaxRAMPercentage=70.0", "-XX:+UseParallelGC", "-jar", "GAI_Owners.jar"]
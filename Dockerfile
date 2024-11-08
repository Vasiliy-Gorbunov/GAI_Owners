FROM openjdk:21-jdk-slim
WORKDIR /app
COPY /target/GAI_Owners-0.0.1-SNAPSHOT.jar /app/GAI_Owners.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "GAI_Owners.jar"]
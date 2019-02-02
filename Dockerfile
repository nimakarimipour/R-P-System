FROM maven:3.5.3-jdk-9-slim

WORKDIR /app
ADD pom.xml /app
RUN mvn install
ADD . /app
RUN mvn clean package
EXPOSE 8080
CMD ["java" , "-jar", "target/rewardAndPunishment-1.0-SNAPSHOT.jar", "server", "backend-docker.yml"]

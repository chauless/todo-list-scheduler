FROM maven:3.8.6-openjdk-18-slim
LABEL author="Matvei Morenkov"

COPY pom.xml .
RUN mvn clean package -DskipTests

WORKDIR /app
COPY target/task-tracker-scheduler-0.0.1-SNAPSHOT.jar /app/task-tracker-scheduler.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "task-tracker-scheduler.jar"]
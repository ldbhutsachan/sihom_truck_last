#FROM openjdk:11-jre-slim
FROM eclipse-temurin:11-jre-jammy
LABEL author="sihom-api"
RUN mkdir /app
RUN mkdir /images
COPY ./target/*.jar /app/run.jar
EXPOSE 9988
CMD ["java", "-jar", "-Duser.timezone=GMT+7","/app/run.jar"]




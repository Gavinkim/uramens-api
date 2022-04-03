FROM openjdk:11-jdk-slim
ENV	ACTIVE_PROFILE prod
EXPOSE 9090
COPY build/libs/**.jar uramens-api.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-jar","/uramens-api.jar"]
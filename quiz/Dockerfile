FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11
COPY --from=MAVEN_BUILD target/*.jar quiz.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "quiz.jar"]
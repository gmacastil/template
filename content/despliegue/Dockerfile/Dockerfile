FROM aproddevopsrc.azurecr.io/base/openjdk:11-jre-slim-buster
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-Duser.timezone=CO","-jar","/app.jar"]

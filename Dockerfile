FROM openjdk:8-jdk-alpine
MAINTAINER Luiz Gustavo S. de Souza
VOLUME /tmp
COPY target/moneycount-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
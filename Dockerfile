FROM java:8-jdk-alpine

COPY ./target/camel-sample-rest-api-1.0.0-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch camel-sample-rest-api-1.0.0-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","camel-sample-rest-api-1.0.0-SNAPSHOT.jar"]
FROM openjdk:8-jdk-alpine

# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring

RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/dealsservice

# ARG JAR_FILE=/target/deals-service-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} /usr/local/dealsservice/deals-service-0.0.1-SNAPSHOT.jar


#ADD @project.build.finalName@.jar /usr/local/dealsservice/
COPY deals-service-0.0.1-SNAPSHOT.jar /usr/local/dealsservice
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
#Start with a base image containing Java runtime
FROM openjdk:11-slim

# Add Maintainer Info
LABEL maintainer="Joseph Calver joseph@josephcalver.com"

# The application's jar file (defined in pom.xml)
ARG JAR_FILE

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Add docker-compose-wait tool
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

#execute the application
CMD ["java","-jar","/app.jar"]
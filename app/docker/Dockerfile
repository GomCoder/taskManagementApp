FROM openjdk:8-jre-alpine

ADD app.jar /opt/taskManagementApp/app.jar
ADD application-docker.properties /config/application-docker.properties

EXPOSE 8080 9000

ENTRYPOINT ["java", "-jar", "/opt/taskManagementApp/app.jar"]


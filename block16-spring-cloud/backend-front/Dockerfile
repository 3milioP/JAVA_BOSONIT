FROM openjdk:20
EXPOSE 8082
COPY /target/*.jar /usr/local/lib/backend-front-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/backend-front-0.0.1-SNAPSHOT.jar"]
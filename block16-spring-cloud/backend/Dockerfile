FROM openjdk:20
EXPOSE 8081
COPY /target/*.jar /usr/local/lib/Backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/Backend-0.0.1-SNAPSHOT.jar"]
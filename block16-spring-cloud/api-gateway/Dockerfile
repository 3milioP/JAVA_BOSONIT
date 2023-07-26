FROM openjdk:20
EXPOSE 8080
COPY /target/*.jar /usr/local/lib/api-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/api-gateway-0.0.1-SNAPSHOT.jar"]
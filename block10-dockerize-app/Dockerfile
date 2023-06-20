FROM openjdk:20
EXPOSE 8080
COPY /target/*.jar /usr/local/lib/myapp.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/myapp.jar"]

FROM openjdk:20
EXPOSE 8761
COPY /target/*.jar /usr/local/lib/eureka-naming-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/eureka-naming-server-0.0.1-SNAPSHOT.jar"]
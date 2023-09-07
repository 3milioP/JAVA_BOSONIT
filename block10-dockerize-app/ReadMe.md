# Dockerizar aplicación
## Nombre proyecto Maven: block10-dockerize-app
Tiempo estimado: 2 horas.
Crear imagen Docker que incluya una aplicación Spring Boot que se conecte a una base de datos PostgreSQL.
Levantar un servidor de PostgreSQL que no sea accesible desde nuestro Windows, es decir, no mapear el puerto de postgres a nuestro host (“-p 5432:5432”).   El usuario para conectarse será  ‘postgres’ y el password: ‘contrasena’. Se creará la base de datos ‘test’.
Deberemos tener en un contenedor la aplicación de Spring Boot y el servidor de PostgreSQL deberá estar en otro contenedor, dentro de la misma red virtual para que ambos contenedores Docker se vean entre ellos.
Desde Windows deberemos poder hacer peticiones con postman a la aplicación en Spring Boot.

Pistas:

Poner el driver de PostgreSQL y configurar fichero application.properties para conectarte a Postgresql. Mirar este articulo como guía: https://dzone.com/articles/bounty-spring-boot-and-postgresql-database
Crear una red interna: docker network create mynetwork
Ejecutar postgres especificando que este en una red definida por nosotros: docker run --network mynetwork --name postgres_test -ePOSTGRES_USER=postgres -e POSTGRES_PASSWORD=contrasena -e POSTGRES_DB=test postgres

Importante:
Comprobar que no tengáis postgres corriendo en vuestra máquina . Es decir, que quizás lo instalasteis como aplicación y todavía está corriendo. Para comprobarlo lo más fácil, es echar abajo el docker de postgres y si todavía os da un mensaje de password invalido o algo parecido es que está corriendo. El mensaje que os tiene que dar es que no llega al servidor. Por supuesto si está corriendo el postgres en windows, echarlo abajo.

# Testing CRUD
## Nombre proyecto Maven: block7-crud-validation (hay que modificar el proyecto existente).
Hacer testing de ejercicio CRUD básico.
Ejecutar SonarQube sobre el ejercicio y que de un 70% de cobertura del código.


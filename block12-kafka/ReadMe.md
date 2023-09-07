# Kafka
Aplicación sencilla que envía y recibe mensajes a través de un servidor Kafka.

1. Descarga Kafka de la página oficial: https://kafka.apache.org/downloads
2. Descomprime el archivo en C:\Kafka
3. Abre una consola de comandos y ejecuta los siguientes comandos:
```
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```
4. Con Zookeeper ejecutándose, necesitamos agregar el wmic archivo ejecutable que usa Kafka en nuestro sistema PATH
```
set PATH=C:\windows\System32\wbem;%PATH%;
```
5. Desde otra sesión en consola, desde la ruta C:\Kafka ejecuta el siguiente comando para iniciar el servidor Kafka:
```
bin\windows\kafka-server-start.bat config\server.properties
```
6. Desde otra sesión en consola, desde la ruta C:\Kafka ejecuta el siguiente comando para crear un topic:
```
kafka-topics.bat --bootstrap-server localhost:9092 --create --topic miTema --partitions 1 --replication-factor 1
```
7. Con esto ya podrías lanzar la aplicación y probarla. Para probarla en local se puede lanzar dos instacias de la misma aplicación cada una en distinto puerto.



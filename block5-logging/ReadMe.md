# Logging
## Nombre proyecto Maven: block5-logging
Tiempo estimado: 4 horas + 3 horas parte opcional.
Crear programa que escriba los logs tipo Error en un fichero y los de tipo Warning o inferiores solo a consola.
Recordar que todas las configuraciones posibles estan en esta URL: https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html
Pista: el filtro ThresholdFilter puede ser de ayuda.

Parte opcional:
Los ficheros se deberán llamar ‘spring-logging-NN.log', donde NN es el número consecutivo. Hacer que genere un fichero de log nuevo cada vez que se inicie la aplicación y que como máximo haya 5 ficheros.
Por ejemplo, el primer fichero será: spring-logging.log, el segundo spring-logging-01.log, etc.
Cada vez que se genere uno nuevo, se renombrara spring-logging.log a spring-logging-01.log,  spring-logging-01.log a spring-logging-02.log y así sucesivamente. spring-logging-05.log, si existe será borrado y spring-logging-04.log será renombrado a spring-logging-05.log
Configurar el sistema de logs para que si un fichero excede los 5K de longitud también deberá rotar.
Aclaración importante:  Para hacer este ejercicio completamente habría que crear un fichero ‘logback-spring.xml” o “logback.xml”.
Como crear ese fichero está fuera del ámbito del curso solo se requiere conocer muy por encima las posibilidades que tenemos a la hora de configurar nuestro sistema de logging.
Existe documentación en la página oficial del proyecto Logback  (https://logback.qos.ch/) pero NO es necesario hacerlo. Estableciendo propiedades en el fichero application.properties como “logging.file.name” o "logging.logback.rollingpolicy.max-file-size"  normalmente valdrá para configurar nuestro sistema de log. (ver https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.logging.file.name )
Sí se requiere utilizar los diferentes niveles de log (warning, info, etc)  y sacar los warning o inferiores por consola y los de error dejarlos en un fichero.

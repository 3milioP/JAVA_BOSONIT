# Controladores de Persona
## Nombre proyecto Maven: block6-person-controllers
Tiempo estimado: 4 horas.
1) Crear aplicación con dos clases de controlador. Controlador1.java y Controlador2.java
   En Controlador1, en la URL /controlador1/addPersona, tipo GET, en los headers tiene que recibir, el nombre, población y edad.
   Utilizando una clase de servicio, se creará un objeto Persona. La llamada devolverá el objeto Persona creado.
   En otra clase, crear el Controlador2, en la URL /controlador2/getPersona tiene que devolver el objeto Persona recibido en el Controlador1, multiplicando la edad por dos.
   Importante: Utilizar inyección de dependencias.

2)  Al arrancar el programa crear una lista de objetos tipo Ciudad. Ciudad tendrá dos campos: nombre(String) y numeroHabitantes (int)
    En controlador1, en la URL /controlador1/addCiudad, petición tipo POST, se añadirá una ciudad a la lista.
    En controlador2, en la URL /controlador1/getCiudades, petición tipo GET, se devolverá la lista de las ciudades existentes.
    Pista: Utilizar inyección de dependencias. (@Bean , @Service, etc..)
3) Crear 3 objetos Persona diferentes con funciones que tengan la etiqueta @Bean.  La primera función pondrá el nombre a ‘bean1’, el segundo a “bean2” y el tercero a “bean3”. Usar @Qualifiers
   En un controlador con la URL /controlador/bean/{bean} dependiendo del parámetro mandado devolver cada uno de los Beans. Asi: /controlador/bean/bean1 devolverá el objeto cuyo nombre sea bean1 y así sucesivamente.  

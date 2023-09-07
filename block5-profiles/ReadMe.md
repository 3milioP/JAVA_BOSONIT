# Perfiles
## Nombre proyecto Maven: block5-profiles
Tiempo estimado: 4 horas.
Crear programa que reciba el nombre del entorno vía JVM y cargue el fichero de propiedades correspondiente al entorno. El programa debe tener 3 ficheros de propiedades, uno para cada entorno: local, INT y PRO.
Crear el programa modo consola, usando CommandLineRunner.
Cada fichero de propiedades debe contener las mismas propiedades pero diferente información. Para el ejercicio, deben contener únicamente las siguientes propiedades: “spring.profiles.active” y “bd.url”.

Para local: (Con variable spring.profiles.active=local)
bd.url=localhost:5432

Para INT:  (Con variable spring.profiles.active=INT)
bd.url=int.bosonit.com:5432

Para PRO:  (Con variable spring.profiles.active=PRO)
bd.url=pro.bosonit.com:5432

En formato YAML (YML)
bd.url: pro.bosonit.com:5432




Tener en cuenta que la variable “spring.profiles.active” solo debe ser puesto y, no es obligatorio, en el application.properties o application.yml . NO se debe poner en los demás ficheros “application-PERFIL..”
Nota: Recordad guardar las Run Configuration del ejercicio.
Ayuda: Mirar la pagina del ‘profe’, si se resiste:  Perfiles en Spring Boot :: Documentacion en español  

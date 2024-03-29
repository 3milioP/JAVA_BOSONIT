# CRUD con validación
## Nombre proyecto Maven: block7-crud-validation
Tiempo estimado: 8 horas parte 1 + 4 horas parte 2 + 8 horas parte 3.
Realizar commit al acabar cada apartado del ejercicio.
Parte 1 - Crear CRUD de tabla Persona
Siendo ahora la tabla persona con la siguiente definición:

table persona
{
id_persona int [pk, increment]
usuario string [not null max-length: 10 min-length: 6]
password string  [not null]
name string [not null]
surname string  
company_email string  [not null ]
personal_email string [not null]
city string [not null]
active boolean  [not null]
created_date date  [not null]
imagen_url string
termination_date date
}

Realizar validaciones necesarias con lógica en java (no usar etiqueta @Valid)
if (usuario==null) {throw new Exception(“Usuario no puede ser nulo”); }
if (usuario.length()>10) { throw  new Exception(“Longitud de usuario no puede ser superior a 10 caracteres)
…
EI ID autoincremental se puede hacer con estas simples instrucciones:
@GeneratedValue
private int id;

Poner 3  endpoints en la búsqueda.
Buscar por ID
Buscar por nombre de usuario (campo usuario)
Mostrar todos los registros.
Usar DTOS, interfaces y clases de servicio.

Nota: No es necesario crear la carpeta repository. Para hacer más simple el ejercicio se pueden poner todos los servicios en application.
Parte 2 - Crear excepciones
Crear dos tipos de excepción al CRUD anteriormente realizado:
EntityNotFoundException que generará un código HTTP 404. Se lanzará cuando no se encuentren registros en un findById o si al borrar o modificar un registro este no existe.
UnprocessableEntityException que devolverá un 422 (UNPROCESSABLE ENTITY) cuando la validación de los campos no cumpla los requisitos establecidos, al modificar o insertar un registro.
Ambas excepciones deberán devolver un objeto llamado CustomError con los campos:
Date timestamp;
Int HttpCode;
String mensaje; // Devolverá el mensaje de error.
Parte 3 - Relaciones entre entidades
En este ejercicio se trabajará con esta estructura de tablas.
Atención: No hay que crearlas sino dejarlas que las cree nuestra aplicación por nosotros al arrancarla.

table student
{
id_student string [pk, increment]
id_persona string [ref:-  persona.id_persona] -- Relación OneToOne con la tabla persona.
num_hours_week int   [not null] -- Número de horas semanales del estudiante en formación
coments string
id_profesor string [ref: > profesor.id_profesor] -- Un estudiante solo puede tener un profesor.
branch string [not null] -- Rama principal delestudiante (Front, Back, FullStack)
}
table profesor
{
id_profesor string [pk, increment]
id_persona string [ref:- persona.id_persona] -- Relación OneToOne con la tabla persona.
coments string
branch string [not null] -- Materia principal que imparte. Por ejemplo: Front
}
table asignatura ## Asignaturas que tendrá cada estudiante asignadas
{
id_asignatura String [pk, increment]
id_student string [ref: > student.id_student] -- Un estudiante puede tener N asignaturas
asignatura string  -- Nombre de asignatura. Ejemplo: HTML, Angular, SpringBoot...
coments string
initial_date date [not null], -- Fecha en que estudiante empezó a estudiar la asignatura
finish_date date  -- Fecha en que estudiante termina de estudiar la asignatura
}

Diagrama:

--- Ejemplo de entidades en JPA –  
¡¡ SON SOLO EJEMPLOS DE CÓMO SE PUEDEN RELACIONAR. NO COPIAR Y PEGAR !!

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
Integer id_student;
@OneToOne
@JoinColumn(name = "id_persona")
Persona persona;
@Column(name = "horas_por_semana")
Integer num_hours_week;
@Column(name = "comentarios")
String coments;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_profesor")
Profesor profesor;
@Column(name = "rama")
String branch;
@OneToMany
List<Alumnos_Estudios> estudios;
}
@Entity
@Table(name = "estudios")
@Getter
@Setter
public class Alumnos_Estudios {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
Integer id_study;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "profesor_id")
Profesor profesor;
@ManyToMany(cascade = CascadeType.ALL)
Student student;
@Column(name = "asignatura")
String asignatura;
@Column(name = "comentarios")
String comment;
@Column(name = "initial_date")
Date initial_date;
@Column(name = "finish_date")
Date finish_date;
. . .

La relación gráfica entre las tablas la tenéis en este enlace: https://dbdiagram.io/d/60938b29b29a09603d139d64
La relación de la tabla student y profesor con Persona es one-to-one
La relación entre estudiante y profesor es tipo one-to-many. Un estudiante puede tener un solo profesor y un profesor puede tener N estudiantes.
Un estudiante puede estar en N estudios por lo cual la relación es many-to-many.
Realizar CRUD sobre TODAS las tablas. Cuando se busque por ID la persona, si es estudiante, debe devolver los datos de ‘estudiante’, ‘profesores’ y los estudios realizados por el estudiante. Si la persona es profesor, sacará los datos de la entidad profesor relacionada, los estudiantes a su cargo, y para cada estudiante los estudios realizados
Como se puede apreciar los índices son tipo String y autoincrementales. Recordar que en la primera parte de este ejercicio tenéis cómo crear campos autoincrementales del tipo String.
Realizar CRUD de estudiante con endpoints similares a los de persona. De momento, no poner relación con profesor ( id_profesor )
Modificar endpoint “/estudiante/{id}” para que acepte el QueryParam ‘ouputType’. Por defecto deberá tener el valor ‘simple’ (es decir si no se manda cogerá como valor el literal ‘simple’).
En el caso de que el valor de ‘outputType’ sea ‘simple‘,  la consulta devolverá sólo los datos del estudiante. En el caso de que sea ‘full’ devolverá los datos del estudiante y de la persona asociada.
Ejemplo:
GET “http://localhost:8080/{id}?outputType=full”
{
id_student : 1,
num_hours_week : 40,
coments: “No puede trabajar por las tardes”,
id_persona: 1,
user: ‘francisco’,
password: “secreto”,
Name: “Francisco”,
Surname: “perez”,
company_email: “francisco@bosonit.com”,
personal_email: “francisco@gmail.com”,
city : “zaragoza”,
Active: true
created_date: ‘2021-10-03'
imagen_url: “http://pictures.com/imagen1.png”,
termination_date: null
}

Como se puede observar habrá que crear diferentes DTOs de salida.
Realizar CRUD de las tablas restantes.
Tener en cuenta que UNA persona solo puede ser o profesor o estudiante.
En TODOS los endpoints de búsqueda de la entidad persona (por ID, por nombre o todas las personas), aceptar un parámetro que indique si debe devolver solo los datos de la persona o también los de estudiante o profesor si fuera alguno de ellos.
Crear endpoint en ‘estudiante_asignatura’ que permita buscar por id de estudiante. Este endpoint sacará todas las asignaturas que tiene un estudiante.
Realizar chequeos lógicos: ¿Borrar persona si tiene un estudiante/profesor asignado? ¿Borrar asignatura si tiene estudiantes asignados?
Realizar endpoints de estudiante para asignarle una o más asignaturas. Crear otro endpoint para desasignar una o más asignaturas (deberá poder recibir una lista de IDs de asignaturas).

# Parte 4 - Feign
En el CRUD anteriormente realizado incluir en la entidad Persona el siguiente endpoint.
@RestControler(“/profesor/{id})
ProfesorOutputDto GET getProfesor(@PathVariable int id)

Este endpoint deberá llamar al de la entidad profesor que devuelve el profesor por id, en el puerto 8081 usando RestTemplate.
Usando  Feign:
Crear la llamada anterior usando  feign.
Para hacer la prueba deberemos tener dos instancias de nuestro programa lanzado. Una corriendo en el puerto 8080 y otra en el puerto 8081. La prueba consistirá en llamar a GET localhost:8080/persona/profesor/1 y que ésta llamada llame a localhost:8081/profesor/1 devolviéndonos los datos del profesor.
Importante: Para usar feign hay que añadir la dependencia ‘OpenFeign” que es parte del paquete Spring Cloud. Este paquete es independiente de Spring Boot y  hay que tener cuidado con las versiones. Un Spring Cloud, versión X, puede no funcionar con Spring Boot Y.
Para asegurarse de que no hay problemas lo más fácil es crear el proyecto,  desde https://start.spring.io, con la versión de Spring Boot deseada y  añadiendo OpenFeign, comprobar qué versión se añade en la página web.

# CORS
## Nombre proyecto Maven: block7-crud-validation (hay que modificar el proyecto existente)
Tiempo estimado: 2 horas.
Permitir realizar peticiones entre dominios modificando el módulo del ejercicio CRUD con validación para la ruta ‘/person’.
Para probar ir a la página: https://codepen.io/de4imo/pen/VwMRENP .
Actualizar el back para que funcionen estas llamadas:
Alta: Tipo. POST -  Ruta: http://localhost:8080/addperson  
Objeto mandado:
'usuario': document.getElementById('username').value,
'password': document.getElementById('passwd').value,
'name': document.getElementById('name').value,
'surname': document.getElementById('surname').value,
'company_email': document.getElementById('emailcomp').value,
'personal_email': document.getElementById('emailpers').value,
'city': document.getElementById('city').value,
'active': document.getElementById('active').checked,
'created_date': document.getElementById('created_date').value,
'imagen_url': document.getElementById('imagen_url').value,
'termination_date': document.getElementById('finish_date').value,
})  
Consulta: http://localhost:8080/getall
List<Person> (Mismos campos que en el add)


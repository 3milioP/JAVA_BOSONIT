# Login
## Nombre proyecto Maven: block7-crud-validation (hay que modificar el proyecto existente)
Se añadirá a la tabla personas el campo
admin  boolean  [not null]

Después se creará el endpoint /login que pedirá usuario y password  devolviendo un token JWT firmado si los datos son válidos comprobando contra la tabla ‘personas’.
Si el usuario es admin podrá acceder a todos los endpoints, si no lo es, solo a los endpoints de consulta.
Tener en cuenta que en este ejercicio NO estamos usando OAUTH2.

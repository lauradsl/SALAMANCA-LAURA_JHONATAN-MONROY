# Clínica Odontológica
Proyecto integrador backend. Esta aplicación  permite: el registro de pacientes y odontólogos, la gestión de citas y 
turnos. 

### Requerimientos
Esta aplicación se desarrolló utilizando el framework Spring Boot con Java 17. Se gestionaron las dependencias con Maven y se utilizó el motor de base de datos H2. Además, se implementó el framework Hibernate para el manejo de objetos relacionales a través de JPA.

### Endpoints
Para la documentación de los endpoints se usó Swagger. 
- Ingresar a http://localhost:8080/swagger-ui/index.html
- Ver archivo en *src/main/resources/api/prueba-clinica-odontologica.json*

Tambien se adjunta la colección de Postman en: *Clinica_Odontologica.postman_collection*


### Frontend
El fronted fue construido con HTML y CSS. Las vistas se encuentran en */main/resources/static* y permiten el 
registro de 
Pacientes, Odontólogos y Turnos (Estos tienen la conexión con el Backend)

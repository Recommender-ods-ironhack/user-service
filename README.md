# user-service

## Descripción

El microservicio user-service gestiona la información de los usuarios dentro del sistema de recomendación de ropa. Proporciona operaciones CRUD, permitiendo la creación, consulta, actualización parcial y eliminación de usuarios. Está integrado en una arquitectura de microservicios mediante Eureka y es accesible a través de un API Gateway.

## Características

- **Respuestas estandarizadas:** Todos los endpoints devuelven respuestas encapsuladas en ResponseEntity para un manejo adecuado de códigos de estado HTTP y mensajes.
- **Operaciones CRUD:** Permite crear, obtener, actualizar parcialmente y eliminar usuarios.
- **Manejo de Excepciones:** Respuestas adecuadas para casos como usuarios no encontrados.
- **Integración con Microservicios:** Registro en Eureka para descubrimiento por otros servicios y comunicación a través de un Gateway.

## Tecnologías Utilizadas

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)
- [Lombok](https://projectlombok.org/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Eureka](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html)

## Endpoints

> **Nota:** Todas las respuestas se devuelven encapsuladas en ResponseEntity.

### GET /api/user/{id}
- **Función:** Devuelve un usuario específico a partir de su ID.
- **Response:** ResponseEntity<User> o mensaje de error si no se encuentra.

### POST /api/user
- **Función:** Crea un nuevo usuario.
- **Request Body:** Objeto User.
- **Response:** ResponseEntity<User>

### PATCH /api/user/{id}
- **Función:** Realiza una actualización parcial del usuario especificado.
- **Request Body:** Objeto UserPatchDTO que contiene los cambios.
- **Response:** ResponseEntity<User> o mensaje de error si no se encuentra.

### DELETE /api/user/{id}
- **Función:** Elimina el usuario identificado por su ID.
- **Response:** ResponseEntity<String> con mensaje de confirmación o mensaje de error si no se encuentra.

## Configuración y Ejecución

### Requisitos Previos

- **Base de Datos:** MySQL debe estar instalado y configurado.
- **Eureka:** La instancia de Eureka debe estar funcionando antes de iniciar el microservicio.

### Configuración de la Base de Datos

Crea la base de datos en MySQL utilizando la siguiente URL:

jdbc:mysql://localhost:3306/user

Asegúrate de insertar los datos iniciales necesarios para el correcto funcionamiento del servicio.

### Archivo de Configuración

Dentro de la carpeta src/main/resources, crea el archivo application.properties con la siguiente configuración, adaptándola a tu entorno:

```properties
# Nombre de la aplicación para el registro en Eureka
spring.application.name=user

# Configuración del servidor
server.port=8081

# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/user
spring.datasource.username=root
spring.datasource.password=prueba123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Configuración de Eureka para el descubrimiento de servicios
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/

# Configuración de JPA para la gestión del esquema de la base de datos
spring.jpa.hibernate.ddl-auto=update


```
## Descripción de las propiedades

- **spring.application.name:** Define el nombre del microservicio para su registro en Eureka.
- **server.port:** Especifica el puerto en el que se ejecutará el servicio.
- **spring.datasource.\*:** Parámetros necesarios para la conexión a la base de datos MySQL, incluyendo URL, credenciales y el driver JDBC.
- **spring.jpa.properties.hibernate.dialect:** Informa a Hibernate del dialecto SQL a utilizar, optimizando la interacción con MySQL.
- **eureka.client.serviceUrl.defaultZone:** Proporciona la URL del servidor Eureka para habilitar el registro y descubrimiento del servicio.
- **spring.jpa.hibernate.ddl-auto:** Controla la estrategia de actualización del esquema de la base de datos; en este caso, se actualiza automáticamente al iniciar el servicio.

## Ejecución del Microservicio

1. **Inicia la instancia Eureka:**  
   Verifica que Eureka esté en funcionamiento para asegurar el registro correcto del servicio.

2. **Ejecuta el microservicio:**  
   Ejecuta la clase principal UserServiceApplication.java desde tu IDE o mediante la línea de comandos.
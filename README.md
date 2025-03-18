# Web Service CRUD Application - Spring Boot & MongoDB

Este proyecto es una aplicación RESTful desarrollada con Spring Boot que implementa un CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar productos almacenados en una base de datos MongoDB. Se han realizado pruebas unitarias con Junit & Mockito. Se ha utilizado Docker para la contenerización, Swagger para la documentación de la API y SonarQube para el análisis de calidad de código.

---

## 🛠️ Tecnologías Utilizadas
- **Spring Boot**: Framework para aplicaciones Java.
- **Spring Data MongoDB**: Para la persistencia en MongoDB.
- **MongoDB**: Base de datos NoSQL utilizada.
- **Swagger (OpenAPI)**: Documentación de la API.
- **Docker**: Contenerización de la base de datos.
- **Postman**: Herramienta para probar APIs.
- **JUnit & Mockito**: Pruebas unitarias.
- **SonarQube**: Análisis de calidad de código.
- **MongoDB Compass**: Herramienta visual para gestionar la base de datos.

---

## 🚀 Funcionalidades
1. **GET/ Obtener todos los Productos**: Devuelve una lista de todos los productos registrados.
2. **GET/ Obtener productos por ID**: Recupera la información de un producto mediante su ID.
3. **POST/ Crear producto**: Permite registrar un nuevo producto en la base de datos.
4. **PUT/ Modificar producto por ID**: Modifica un producto existente mediante su ID.
5. **DELETE/ Borrar producto por ID**: Elimina un producto basado en su ID.

---

## 📂 Estructura del Proyecto

- **WebserviceEjercicioApplication.java**: Clase principal
- **SwaggerConfig.java**: Configuración de Swagger
- **ProductoController.java**: Controlador REST
- **Productos.java**: Modelo de datos
- **ProductoRepository.java**: Repositorio de MongoDB
- **ProductoService.java**: Lógica de negocio
- **BadRequestException.java**: Excepción personalizada
- **GlobalExceptionHandler.java**: Manejador global de errores
- **ResourceNotFoundException.java** Excepción de recurso no encontrado
- **ProductoServiceJUnit_Test.java**: Pruebas con JUnit
- **ProductoServiceMockito_Test.java**: Pruebas con Mockito
- **application.properties**: Configuración de la aplicación
- **pom.xml**: Configuración de Maven
- **.gitignore**: Archivos ignorados

---

## ⚙️ Requisitos
1. **Java**: JDK 21 o superior.
2. **Spring Boot**: Framework principal.
3. **Base de datos**: MongoDB.
4. **Docker**: Para la contenerización de la aplicación.
5. **Herramienta de prueba**: Postman o cualquier cliente REST.
6. **SonarQube**: Para el análisis de calidad de código.
7. **MongoDB Compass**: Para administrar la base de datos visualmente.

## 🔧 Configuración y Ejecución

### 1. Clona este repositorio
git clone https://github.com/Apermar87/webserviceEjercicio.git
cd webserviceEjercicio

### 2. Configuración de MySQL con Docker
Crea un contenedor Docker para la base de datos MongoDB:

docker run --name mongodb-container -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin -p 27017:27017 -d mongo

### 3. Configura la base de datos en `application.properties`:
spring.data.mongodb.uri=mongodb://admin:admin@mongodb:27017/mi_base_de_datos?authSource=admin

### 4. Ejecuta el proyecto con el siguiente comando:
mvn spring-boot:run

### 5. Prueba las rutas REST con Postman o navegador
- GET /obtenerProductos
- GET /obtenerProductos/{id}
- POST /crearProducto
- PUT /modificarProducto/{id}
- DELETE /borrarProducto/{id}

---

## 📝 Documentación Swagger
Swagger está disponible en:
http://localhost:8080/swagger-ui.html

## 🧪 Pruebas
Ejecutar pruebas unitarias con JUnit y Mockito:

mvn test

## 📊 Análisis de Código con SonarQube
Para ejecutar un análisis de calidad de código con SonarQube:

mvn sonar:sonar

## 🗃️ Gestión de Base de Datos con MongoDB Compass
MongoDB Compass permite administrar la base de datos de manera visual. Puedes conectarte con la siguiente URI:

mongodb://admin:admin@mongodb:27017/mi_base_de_datos?authSource=admin

## 🌟 Próximos Pasos
- Añadir validación de datos: Mejorar la validación de entradas en las APIs.
- Implementar autenticación: Proteger las rutas REST mediante autenticación y autorización.
- Optimización de consultas: Mejorar el rendimiento en el acceso a MongoDB

# WebserviceEjercicio

## **Estructura del Proyecto**

webserviceEjercicio/ ├── src/main/java/com/example/webserviceEjercicio/ │ ├── WebserviceEjercicioApplication.java # Clase principal │ ├── config/ │ │ ├── SwaggerConfig.java # Configuración de Swagger │ ├── controllers/ │ │ ├── ProductoController.java # Controlador REST │ ├── models/ │ │ ├── Productos.java # Modelo de datos │ ├── repositories/ │ │ ├── ProductoRepository.java # Repositorio de MongoDB │ ├── services/ │ │ ├── ProductoService.java # Lógica de negocio │ ├── exception/ │ │ ├── BadRequestException.java # Excepción personalizada │ │ ├── GlobalExceptionHandler.java # Manejador global de errores │ │ ├── ResourceNotFoundException.java # Excepción de recurso no encontrado ├── src/test/java/com/example/webserviceEjercicio/ │ ├── services/ │ │ ├── ProductoServiceJUnit_Test.java # Pruebas con JUnit │ │ ├── ProductoServiceMockito_Test.java # Pruebas con Mockito ├── src/main/resources/ │ ├── application.properties # Configuración de la aplicación ├── pom.xml # Configuración de Maven ├── .gitignore # Archivos ignorados

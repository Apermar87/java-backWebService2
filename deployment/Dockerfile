# Usamos una imagen base de Java 22
FROM openjdk:22

# Establecemos el directorio de trabajo en el contenedor
WORKDIR /app

# Copiamos el archivo JAR generado al contenedor
COPY target/webserviceEjercicio-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto en el que la aplicación Spring Boot se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]



# Utiliza la imagen oficial de OpenJDK 21 como base
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y la carpeta src desde tu proyecto a la imagen
COPY pom.xml .
COPY src /app/src

# Instalar las dependencias y compilar el proyecto usando Maven
RUN apt-get update && apt-get install -y maven
RUN mvn clean install -DskipTests

# Copiar el archivo JAR generado en la etapa de compilación al contenedor
COPY target/webserviceEjercicio-0.0.1-SNAPSHOT.jar /app/webserviceEjercicio.jar

# Exponer el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "/app/webserviceEjercicio.jar"]

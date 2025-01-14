# Usar la imagen oficial de OpenJDK 21 como base
FROM openjdk:21-jdk-slim

# Crear el directorio /app
RUN mkdir -p /app

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR desde la carpeta target al contenedor
COPY target/factured-example-trello-0.0.1-SNAPSHOT.jar /app/app.jar

# Listar el contenido del directorio /app para verificar que el archivo JAR esté presente
RUN ls -l /app

# Darle permisos de ejecución al archivo JAR
RUN chmod +x /app/app.jar

# Exponer el puerto 8080 para la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Imagen base con Java (ajusta la versión que uses, por ejemplo openjdk:17)
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el jar generado al contenedor
COPY target/api-user-flcode-0.0.1-SNAPSHOT.jar app.jar

# Puerto que usará la app
EXPOSE 8083

# Comando para arrancar la app
ENTRYPOINT ["java","-jar","app.jar"]

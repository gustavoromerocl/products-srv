# Utiliza una imagen oficial de Maven con OpenJDK 17
FROM maven:3.8.8-eclipse-temurin-17 as builder

WORKDIR /app

# Copia los archivos necesarios para construir la aplicación
COPY pom.xml .
COPY src ./src

# Construye el proyecto con Maven
RUN mvn clean package -DskipTests

# Usa OpenJDK en la etapa de ejecución
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia el archivo JAR desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

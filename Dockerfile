# Etapa 1: Compilación con Maven y Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar configuración de Maven y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final optimizada y ligera con JRE 17
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el artefacto compilado
COPY --from=build /app/target/SistemaPosFinal-0.0.1-SNAPSHOT.jar app.jar

# Puerto dinámico de Render
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

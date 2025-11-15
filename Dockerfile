FROM maven:3.9-eclipse-temurin-17

WORKDIR /app

# Copiar projeto
COPY pom.xml .
COPY src ./src

# Compilar
RUN mvn clean compile dependency:copy-dependencies -DoutputDirectory=target/lib

# Expor porta
EXPOSE 8080

# Rodar aplicação
CMD ["java", "-cp", "target/classes:target/lib/*", "Principal"]
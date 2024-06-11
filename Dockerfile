# Usar a imagem base do Ubuntu para construção
FROM ubuntu:latest AS build

# Instalar Java e Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos da aplicação para o contêiner
COPY . .

# Compilar e empacotar a aplicação
RUN mvn clean install

# Usar uma imagem mais leve para a execução
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Expor a porta da aplicação
EXPOSE 8080

# Copiar o JAR gerado para o contêiner
COPY --from=build /app/target/stagease-*.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

# Usa a imagem oficial do PostgreSQL
FROM postgres:latest

# Configura variáveis de ambiente para criar usuário, senha e banco de dados
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=root
ENV POSTGRES_DB=granja

# Copia arquivos de inicialização SQL (opcional)
# Caso tenha scripts SQL para executar ao iniciar o contêiner, copie-os
# para a pasta padrão do PostgreSQL
COPY ./init.sql /docker-entrypoint-initdb.d/

# Expõe a porta padrão do PostgreSQL
EXPOSE 5432

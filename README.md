# 🦆 Granja de Patos API

API REST desenvolvida em Java para gerenciar uma granja de patos, com funcionalidades como cadastro de patos e clientes, vendas, aplicação de descontos e e geração de relatórios.

---

# Diagrama de dados
![granja - public - duck](https://github.com/user-attachments/assets/0a17c310-6e16-45b7-b86a-d2daf9b699aa)


## 🛠 Tecnologias Utilizadas

### Backend
- **Java 21**: Linguagem principal para o desenvolvimento.
- **Spring Boot**: Framework para criação da API REST.
- **JPA/Hibernate**: Persistência e mapeamento objeto-relacional.
- **MySQL/PostgreSQL**: Banco de dados relacional para armazenar informações.

### Relatórios
- **Apache POI**: Geração de relatórios em formato Excel.
- **Jasper Report**: Geração de relatórios em formato PDF.

### Containerização
- **Docker**: Para facilitar a configuração e execução do ambiente.

### Gerenciamento de Banco de Dados
- **Flyway** ou **Liquibase**: Controle de versão e migração do banco de dados.

### Testes
- **JUnit**: Para validação das funcionalidades com testes unitários.

---

## 🚀 Passos para Executar a API

### Requisitos
- **Java 21**
- **Maven 3.3.2**
- **PostgreSQL**
- **Docker** (opcional)

### 1. Clone o Repositório
```bash 
git clone https://github.com/seu-usuario/granja-de-patos-api.git
cd granja-de-patos-api
```

### 2.Configure o Banco de Dados
Certifique-se de que um banco de dados PostgreSQL está rodando. Edite o arquivo `src/main/resources/application.propperties` com suas credenciais:
```bash
spring.application.name=granja
spring.datasource.url=jdbc:postgresql://localhost:1234/granja
spring.datasource.username=seu username
spring.datasource.password=sua senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
### 3 Configurações do Flyway
```bash
spring.flyway.enabled=true
spring.flyway.url=jdbc:postgresql://localhost:1234/granja
spring.flyway.user= seu username
spring.flyway.password=sua senha

logging.level.root=INFO
logging.level.com.example.duckfarm=DEBUG

```

### 4🐳 Instruções para Configurar e Usar o Docker
Certifique-se de ter o Docker instalado
Baixe e instale o Docker: https://www.docker.com/.

Build da Imagem Docker
No diretório do projeto, execute:
```bash
docker compose up
```
### 5 📖 Documentação da API com Swagger

A API utiliza o Swagger para fornecer uma interface interativa de documentação.  
Com ele, é possível visualizar e testar os endpoints diretamente pelo navegador.
Acessando o Swagger
Após iniciar a aplicação, acesse o endereço:
http://localhost:8080/swagger-ui.html

Você verá uma interface onde todos os endpoints da API estão documentados e podem ser testados diretamente.

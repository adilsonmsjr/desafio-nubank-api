# API de Clientes e Contatos

API REST desenvolvida com Spring Boot para gerenciamento de clientes e seus contatos, onde cada cliente pode possuir múltiplos contatos associados.

Este projeto foi desenvolvido como desafio técnico, aplicando boas práticas de desenvolvimento backend, arquitetura em camadas e uso de banco de dados relacional.

---

## Funcionalidades

### Clientes
- Criar cliente
- Listar todos os clientes com seus contatos
- Atualizar cliente
- Deletar cliente
- Buscar cliente por ID

### Contatos
- Criar contato vinculado a um cliente existente
- Listar contatos de um cliente específico

---

## Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- Docker & Docker Compose
- JUnit 5
- Mockito
- Swagger (OpenAPI)

---

## Modelo de dados

### Cliente
- id
- nome
- cpf
- contatos (1:N)

### Contato
- id
- nome
- telefone
- cliente (N:1)

---

## Endpoints

### Clientes

- POST /api/cliente
- GET /api/cliente
- GET /api/cliente/{id}
- GET /api/cliente/{id}/contatos
- PUT /api/cliente/{id}
- DELETE /api/cliente/{id}

### Contatos

POST /contato

---

## Como executar o projeto

### Pré-requisitos
- Java 17+
- Maven
- Docker (opcional)

---

###  Docker

docker-compose up -d

### Executando localmente

  ```text
  git clone https://github.com/adilsonmsjr/desafio-nubank-api
  ```
- cd seu-repo
- mvn spring-boot:run

###  Testes

mvn test

Testes com:
- JUnit 5
- Mockito

###  Documentação Swagger

Após iniciar a aplicação:

[Swagger UI](http://localhost:8080/swagger-ui/index.html)

###  Boas práticas aplicadas

- Separação de responsabilidades (SRP)
- Uso de DTOs para isolamento da entidade
- Tratamento global de exceções
- Relacionamento JPA bem definido
- Código testável (Service desacoplado)

###  Autor

Adilson Jr.

###  Objservação

Projeto desenvolvido para fins de estudo e demonstração de habilidades em Java + Spring Boot.

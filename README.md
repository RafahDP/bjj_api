<<<<<<< HEAD
# BJJ API

API REST para gerenciamento de equipes de Jiu-Jitsu, desenvolvida com Spring Boot, Spring Data JPA e MySQL.

## Status do projeto

Atualmente, a API disponibiliza o CRUD de equipes pelo recurso `/teams`. As entidades de atletas e categorias já fazem parte do modelo de dados, mas o controller de atletas ainda não possui endpoints REST implementados.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- MySQL
- Maven

## Pré-requisitos

- JDK 21 ou superior
- MySQL em execução
- Maven instalado ou uso do Maven Wrapper incluído no projeto

## Configuração do banco de dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE bjj;
```

A configuração padrão está em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bjj?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
```

Ajuste `spring.datasource.username` e `spring.datasource.password` conforme o seu ambiente. O Hibernate está configurado com `ddl-auto=update`, portanto as tabelas são atualizadas automaticamente durante a execução.

## Executando a aplicação

No Windows, usando o Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada, por padrão, em:

```text
http://localhost:8080
```

Também é possível gerar o arquivo JAR e executá-lo:

```bash
./mvnw clean package
java -jar target/bjj-api-0.0.1-SNAPSHOT.jar
```

No Windows, substitua `./mvnw` por `mvnw.cmd` quando necessário.

## Endpoints disponíveis

### Listar equipes

```http
GET /teams/
```

Exemplo:

```bash
curl http://localhost:8080/teams/
```

### Buscar equipe por ID

```http
GET /teams/{id}
```

Exemplo:

```bash
curl http://localhost:8080/teams/1
```

### Criar equipe

```http
POST /teams
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "teamName": "Equipe BJJ"
}
```

Exemplo:

```bash
curl -X POST http://localhost:8080/teams \
  -H "Content-Type: application/json" \
  -d '{"teamName":"Equipe BJJ"}'
```

### Atualizar equipe

```http
PUT /teams/{id}
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "teamName": "Equipe BJJ Atualizada"
}
```

Exemplo:

```bash
curl -X PUT http://localhost:8080/teams/1 \
  -H "Content-Type: application/json" \
  -d '{"teamName":"Equipe BJJ Atualizada"}'
```

### Excluir equipe

```http
DELETE /teams/{id}
```

Exemplo:

```bash
curl -X DELETE http://localhost:8080/teams/1
```

## Estrutura principal

```text
src/main/java/com/estudos/bjj_api/
├── BjjApiApplication.java
├── entities/
│   ├── Athlete.java
│   ├── Category.java
│   ├── Match.java
│   └── Team.java
├── repositories/
├── resources/
│   ├── AthleteController.java
│   └── TeamController.java
└── services/
    ├── AthleteService.java
    └── TeamService.java
```

- `entities`: entidades persistidas pelo JPA.
- `repositories`: interfaces de acesso aos dados.
- `services`: regras e operações de negócio.
- `resources`: controllers responsáveis pelos endpoints HTTP.

## Testes

Para executar os testes:

```bash
./mvnw test
```

No Windows:

```powershell
.\mvnw.cmd test
```

## Observações

- O MySQL precisa estar disponível antes de iniciar a aplicação.
- A senha do banco não deve ser versionada em ambientes reais. Prefira variáveis de ambiente ou configuração externa.
- O relacionamento entre `Team`, `Athlete` e `Category` está modelado nas entidades, mas os endpoints dessas funcionalidades ainda podem ser ampliados.
=======
# bjj_api
API RESTful desenvolvida em Java com Spring Boot para gerenciamento de academias, equipes e chaves de torneios de Jiu-Jitsu. 🥋
>>>>>>> 53123799405274b50900b7e0ccf1cdf4647376e6
# bjj_api

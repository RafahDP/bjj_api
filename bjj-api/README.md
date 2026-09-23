# BJJ API

API REST para gerenciamento de equipes, atletas, categorias e lutas de Jiu-Jitsu. O projeto foi desenvolvido com Java e Spring Boot, utilizando Spring Data JPA para persistência dos dados em MySQL.

## Funcionalidades

- Cadastro, consulta, atualização e remoção de equipes.
- Cadastro, consulta, atualização e remoção de atletas.
- Cadastro, consulta, atualização e remoção de categorias.
- Cadastro, consulta, atualização e remoção de lutas.
- Associação de atletas a equipes e categorias.
- Registro dos participantes, categoria, vencedor e status de uma luta.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- MySQL
- Maven

## Pré-requisitos

- JDK 21 ou superior.
- MySQL em execução.
- Maven instalado ou Maven Wrapper, incluído no projeto.

## Configuração do banco de dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE bjj;
```

A aplicação utiliza as propriedades definidas em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/bjj?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

Ajuste usuário, senha e URL conforme o seu ambiente. Com `ddl-auto=update`, o Hibernate cria ou atualiza as tabelas automaticamente durante a execução. Em ambientes reais, não versione credenciais no arquivo de configuração; prefira variáveis de ambiente ou uma configuração externa.

## Executando a aplicação

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

A API será iniciada em `http://localhost:8080`.

Para gerar e executar o JAR:

```bash
./mvnw clean package
java -jar target/bjj-api-0.0.1-SNAPSHOT.jar
```

No Windows, use `mvnw.cmd` no lugar de `./mvnw` quando necessário.

## Endpoints

Todos os endpoints de coleção seguem o padrão:

| Método | Rota | Descrição |
| --- | --- | --- |
| `GET` | `/teams` | Lista equipes |
| `GET` | `/teams/{id}` | Busca uma equipe |
| `POST` | `/teams` | Cria uma equipe |
| `PUT` | `/teams/{id}` | Atualiza uma equipe |
| `DELETE` | `/teams/{id}` | Remove uma equipe |
| `GET` | `/athletes` | Lista atletas |
| `GET` | `/athletes/{id}` | Busca um atleta |
| `POST` | `/athletes` | Cria um atleta |
| `PUT` | `/athletes/{id}` | Atualiza um atleta |
| `DELETE` | `/athletes/{id}` | Remove um atleta |
| `GET` | `/categories` | Lista categorias |
| `GET` | `/categories/{id}` | Busca uma categoria |
| `POST` | `/categories` | Cria uma categoria |
| `PUT` | `/categories/{id}` | Atualiza uma categoria |
| `DELETE` | `/categories/{id}` | Remove uma categoria |
| `GET` | `/matches` | Lista lutas |
| `GET` | `/matches/{id}` | Busca uma luta |
| `POST` | `/matches` | Cria uma luta |
| `PUT` | `/matches/{id}` | Atualiza uma luta |
| `DELETE` | `/matches/{id}` | Remove uma luta |

As operações de criação devem enviar `Content-Type: application/json`. Consultas de registros inexistentes retornam `404 Not Found`; criações retornam `201 Created`; remoções realizadas retornam `204 No Content`.

### Exemplos de payloads

Criar uma equipe:

```json
{
  "teamName": "Equipe BJJ"
}
```

Criar uma categoria:

```json
{
  "category_name": "Adulto Meio-Pesado",
  "max_weight": 88.3,
  "max_age": 30,
  "belt": "Azul",
  "gender": "Masculino"
}
```

Criar um atleta associado a registros existentes:

```json
{
  "name": "João Silva",
  "team": { "id": 1 },
  "category": { "id": 1 },
  "age": 25,
  "gender": "Masculino",
  "weight": 82.5,
  "belt": "Azul",
  "isFighting": "Não"
}
```

Criar uma luta:

```json
{
  "athlete1": { "id": 1 },
  "athlete2": { "id": 2 },
  "category": { "id": 1 },
  "winner": { "id": 1 },
  "matchStatus": "Finalizada"
}
```

Exemplo de requisição:

```bash
curl -X POST http://localhost:8080/teams \
  -H "Content-Type: application/json" \
  -d '{"teamName":"Equipe BJJ"}'
```

## Modelo de dados

- `Team` possui vários `Athlete`.
- `Category` possui vários `Athlete`.
- `Athlete` pertence a uma `Team` e a uma `Category`.
- `Match` referencia dois atletas, uma categoria e, opcionalmente, o vencedor.

Os relacionamentos são representados por IDs nos payloads JSON, como nos exemplos acima.

## Estrutura do projeto

```text
src/main/java/com/estudos/bjj_api/
├── BjjApiApplication.java
├── entities/
│   ├── Athlete.java
│   ├── Category.java
│   ├── Match.java
│   └── Team.java
├── repositories/
│   ├── AthleteRepository.java
│   ├── CategoryRepository.java
│   ├── MatchRepository.java
│   └── TeamRepository.java
├── resources/
│   ├── AthleteController.java
│   ├── CategoryController.java
│   ├── MatchController.java
│   └── TeamController.java
└── services/
    ├── AthleteService.java
    ├── CategoryService.java
    ├── MatchService.java
    └── TeamService.java
```

- `entities`: entidades persistidas pelo JPA.
- `repositories`: interfaces de acesso aos dados.
- `services`: operações de negócio e acesso aos repositórios.
- `resources`: controllers responsáveis pelos endpoints HTTP.

## Testes

Para executar os testes:

No Windows:

```powershell
.\mvnw.cmd test
```

No Linux ou macOS:

```bash
./mvnw test
```

Os testes de integração da aplicação podem exigir uma instância do MySQL disponível conforme a configuração do projeto.

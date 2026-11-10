# BJJ API

API REST para gerenciamento de equipes, atletas, categorias e lutas de Brazilian Jiu-Jitsu. Desenvolvida em Java com Spring Boot, utiliza Spring Data JPA para persistência dos dados em MySQL.

## Funcionalidades

- CRUD completo de **equipes** (`Team`)
- CRUD completo de **atletas** (`Athlete`)
- CRUD completo de **categorias** (`Category`)
- CRUD completo de **lutas** (`Match`)
- Associação de atletas a equipes e categorias
- Registro de participantes, categoria, vencedor e status de cada luta
- CORS habilitado para qualquer origem (`*`)

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 4.1.1 |
| Spring Web MVC | — |
| Spring Data JPA / Hibernate | — |
| MySQL Connector/J | — |
| Spring Boot DevTools | — |
| Maven | Wrapper incluso |

## Pré-requisitos

- JDK 21 ou superior
- MySQL em execução
- Maven instalado **ou** Maven Wrapper (incluso no projeto — sem necessidade de instalação separada)

## Configuração do banco de dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE bjj;
```

Ajuste as credenciais em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/bjj?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
```

Com `ddl-auto=update`, o Hibernate cria ou atualiza as tabelas automaticamente na inicialização. Em ambientes de produção, prefira variáveis de ambiente ou um gerenciador de segredos em vez de armazenar credenciais diretamente no arquivo.

## Executando a aplicação

**Windows:**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux / macOS:**

```bash
./mvnw spring-boot:run
```

A API será iniciada em `http://localhost:8090`.

Para gerar e executar o JAR:

```bash
./mvnw clean package
java -jar target/bjj-api-0.0.1-SNAPSHOT.jar
```

> No Windows substitua `./mvnw` por `.\mvnw.cmd`.

## Endpoints

A base URL padrão é `http://localhost:8090`. Todos os recursos seguem o padrão REST:

### Teams — `/teams`

| Método | Rota | Descrição | Status de sucesso |
|---|---|---|---|
| `GET` | `/teams` | Lista todas as equipes | `200 OK` |
| `GET` | `/teams/{id}` | Busca uma equipe por ID | `200 OK` |
| `POST` | `/teams` | Cria uma equipe | `201 Created` |
| `PUT` | `/teams/{id}` | Atualiza uma equipe | `200 OK` |
| `DELETE` | `/teams/{id}` | Remove uma equipe | `204 No Content` |

### Athletes — `/athletes`

| Método | Rota | Descrição | Status de sucesso |
|---|---|---|---|
| `GET` | `/athletes` | Lista todos os atletas | `200 OK` |
| `GET` | `/athletes/{id}` | Busca um atleta por ID | `200 OK` |
| `POST` | `/athletes` | Cria um atleta | `201 Created` |
| `PUT` | `/athletes/{id}` | Atualiza um atleta | `200 OK` |
| `DELETE` | `/athletes/{id}` | Remove um atleta | `204 No Content` |

### Categories — `/categories`

| Método | Rota | Descrição | Status de sucesso |
|---|---|---|---|
| `GET` | `/categories` | Lista todas as categorias | `200 OK` |
| `GET` | `/categories/{id}` | Busca uma categoria por ID | `200 OK` |
| `POST` | `/categories` | Cria uma categoria | `201 Created` |
| `PUT` | `/categories/{id}` | Atualiza uma categoria | `200 OK` |
| `DELETE` | `/categories/{id}` | Remove uma categoria | `204 No Content` |

### Matches — `/matches`

| Método | Rota | Descrição | Status de sucesso |
|---|---|---|---|
| `GET` | `/matches` | Lista todas as lutas | `200 OK` |
| `GET` | `/matches/{id}` | Busca uma luta por ID | `200 OK` |
| `POST` | `/matches` | Cria uma luta | `201 Created` |
| `PUT` | `/matches/{id}` | Atualiza uma luta | `200 OK` |
| `DELETE` | `/matches/{id}` | Remove uma luta | `204 No Content` |

> Registros não encontrados retornam `404 Not Found`.

### Exemplos de payloads

**Criar uma equipe:**

```json
{
  "teamName": "Equipe BJJ"
}
```

**Criar uma categoria:**

```json
{
  "category_name": "Adulto Meio-Pesado",
  "max_weight": 88.3,
  "max_age": 30,
  "belt": "Azul",
  "gender": "Masculino"
}
```

**Criar um atleta** (referenciando equipe e categoria existentes pelo ID):

```json
{
  "name": "João Silva",
  "age": 25,
  "gender": "Masculino",
  "weight": 82.5,
  "belt": "Azul",
  "isFighting": false,
  "team": { "id": 1 },
  "category": { "id": 1 }
}
```

**Criar uma luta:**

```json
{
  "athlete1": { "id": 1 },
  "athlete2": { "id": 2 },
  "category": { "id": 1 },
  "winner": { "id": 1 },
  "matchStatus": "Finalizada"
}
```

**Exemplo via curl:**

```bash
curl -X POST http://localhost:8090/athletes \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "age": 25,
    "gender": "Masculino",
    "weight": 82.5,
    "belt": "Azul",
    "isFighting": false,
    "team": { "id": 1 },
    "category": { "id": 1 }
  }'
```

## Modelo de dados

```
Team ──< Athlete >── Category
              │
             Match (athlete1, athlete2, category, winner)
```

| Entidade | Tabela | Campos principais |
|---|---|---|
| `Team` | `team` | `id`, `teamName` |
| `Athlete` | `athlete_tb` | `id`, `name`, `age`, `gender`, `weight`, `belt`, `isFighting`, `team_id`, `category_id` |
| `Category` | `category` | `id`, `category_name`, `max_weight`, `max_age`, `belt`, `gender` |
| `Match` | `match_tb` | `id`, `athlete1_id`, `athlete2_id`, `category_id`, `winner_id`, `matchStatus` |

Relacionamentos:
- `Team` → `Athlete`: um para muitos
- `Category` → `Athlete`: um para muitos
- `Match` → `Athlete` (×3): muitos para um (athlete1, athlete2 e winner)
- `Match` → `Category`: muitos para um

## Estrutura do projeto

```text
bjj-api/
├── src/
│   ├── main/
│   │   ├── java/com/estudos/bjj_api/
│   │   │   ├── BjjApiApplication.java        ← ponto de entrada
│   │   │   ├── entities/
│   │   │   │   ├── Athlete.java
│   │   │   │   ├── Category.java
│   │   │   │   ├── Match.java
│   │   │   │   └── Team.java
│   │   │   ├── repositories/
│   │   │   │   ├── AthleteRepository.java
│   │   │   │   ├── CategoryRepository.java
│   │   │   │   ├── MatchRepository.java
│   │   │   │   └── TeamRepository.java
│   │   │   ├── services/
│   │   │   │   ├── AthleteService.java
│   │   │   │   ├── CategoryService.java
│   │   │   │   ├── MatchService.java
│   │   │   │   └── TeamService.java
│   │   │   └── resources/
│   │   │       ├── AthleteController.java
│   │   │       ├── CategoryController.java
│   │   │       ├── MatchController.java
│   │   │       └── TeamController.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/estudos/bjj_api/
│           └── BjjApiApplicationTests.java
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

| Camada | Pacote | Responsabilidade |
|---|---|---|
| Entidades | `entities` | Mapeamento objeto-relacional com JPA |
| Repositórios | `repositories` | Acesso ao banco via `JpaRepository` |
| Serviços | `services` | Regras de negócio e orquestração |
| Controllers | `resources` | Exposição dos endpoints HTTP REST |

## Testes

**Windows:**

```powershell
.\mvnw.cmd test
```

**Linux / macOS:**

```bash
./mvnw test
```

Os testes de contexto (`BjjApiApplicationTests`) verificam que a aplicação inicializa corretamente. Testes de integração que dependem do banco exigem uma instância do MySQL configurada conforme `application.properties`.

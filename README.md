<h1 align="center">BookNest</h1>

<p align="center">
  REST API для управления личной библиотекой, прогрессом чтения, заметками, цитатами, отзывами, оценками и рекомендациями книг.
</p>

<p align="center">
  Java 21 · Spring Boot 3 · PostgreSQL · MongoDB · Flyway · Docker Compose · JUnit 5 · Testcontainers
</p>

<h2 align="center">О Проекте</h2>

**BookNest** — pet-проект на Java Spring Boot. Его цель — показать не просто CRUD, а аккуратное backend-приложение с разделением ответственности, двумя типами хранилищ и понятной бизнес-логикой.

Проект демонстрирует работу с REST API, PostgreSQL, MongoDB, Flyway, Docker Compose, Strategy Pattern, JUnit 5 и Testcontainers.

<h2 align="center">Возможности</h2>

- добавление пользователей, авторов и жанров;
- добавление, обновление, удаление и поиск книг;
- пагинация и фильтрация книг;
- отслеживание прогресса чтения;
- смена статуса книги;
- выставление оценок и расчет средней оценки;
- сохранение заметок, цитат, отзывов и сессий чтения в MongoDB;
- получение рекомендаций по жанру, автору, рейтингу и истории чтения;
- просмотр статистики чтения, распределения по жанрам и активности по месяцам.

<h2 align="center">Технологический Стек</h2>

| Категория | Технологии |
|---|---|
| Язык | Java 21 |
| Framework | Spring Boot 3 |
| API | REST |
| SQL-хранилище | PostgreSQL |
| NoSQL-хранилище | MongoDB |
| Миграции | Flyway |
| ORM | Spring Data JPA / Hibernate |
| MongoDB integration | Spring Data MongoDB |
| Validation | Jakarta Validation |
| Документация API | Swagger / OpenAPI |
| Тестирование | JUnit 5, Testcontainers |
| Контейнеризация | Docker, Docker Compose |
| Сборка | Maven |

<h2 align="center">PostgreSQL И MongoDB</h2>

В проекте используются два хранилища, потому что данные имеют разную природу.

**PostgreSQL** хранит структурированные данные со строгими связями:

- пользователи;
- авторы;
- жанры;
- книги;
- прогресс чтения;
- оценки.

**MongoDB** хранит гибкие пользовательские документы:

- заметки;
- цитаты;
- отзывы;
- сессии чтения.

Пример документа заметки:

```json
{
  "id": "note-123",
  "userId": 1,
  "bookId": 42,
  "page": 117,
  "text": "Интересная мысль про привычки и системность.",
  "tags": ["habits", "psychology"],
  "mood": "thoughtful",
  "createdAt": "2025-04-14T12:30:00"
}
```

<h2 align="center">Архитектура</h2>

```mermaid
flowchart TD
    Client[Client / Postman / Swagger UI] --> App[BookNest Spring Boot REST API]

    App --> PostgreSQL[(PostgreSQL)]
    App --> MongoDB[(MongoDB)]

    PostgreSQL --> PGData[Users, Authors, Genres, Books, Progress, Ratings]
    MongoDB --> MongoData[Notes, Quotes, Reviews, Reading Sessions]

    App --> Strategy[Recommendation Strategy Registry]

    Strategy --> GenreStrategy[By Genre Strategy]
    Strategy --> AuthorStrategy[By Author Strategy]
    Strategy --> RatingStrategy[By Rating Strategy]
    Strategy --> HistoryStrategy[By Reading History Strategy]
```

<h2 align="center">Основные Модули</h2>

- `user` — пользователи приложения.
- `author` — авторы книг.
- `genre` — жанры книг.
- `book` — книги, поиск, фильтрация и пагинация.
- `progress` — прогресс чтения и текущий статус книги.
- `rating` — оценки пользователей и средняя оценка книги.
- `note` — заметки по книгам в MongoDB.
- `quote` — цитаты из книг в MongoDB.
- `review` — отзывы на книги в MongoDB.
- `session` — сессии чтения в MongoDB.
- `recommendation` — рекомендации книг через Strategy Pattern.
- `statistics` — статистика чтения пользователя.

<h2 align="center">REST API</h2>

```http
POST   /api/users
GET    /api/users
GET    /api/users/{id}

POST   /api/authors
GET    /api/authors
GET    /api/authors/{id}

POST   /api/genres
GET    /api/genres
GET    /api/genres/{id}

POST   /api/books
GET    /api/books
GET    /api/books/{id}
PUT    /api/books/{id}
DELETE /api/books/{id}
GET    /api/books/search?title={title}&author={author}&genre={genre}

POST   /api/books/{bookId}/progress
GET    /api/books/{bookId}/progress?userId={userId}
PUT    /api/books/{bookId}/progress

POST   /api/books/{bookId}/rating
GET    /api/books/{bookId}/rating?userId={userId}
GET    /api/books/{bookId}/ratings

POST   /api/books/{bookId}/notes
GET    /api/books/{bookId}/notes?userId={userId}
GET    /api/notes/{noteId}
DELETE /api/notes/{noteId}

POST   /api/books/{bookId}/quotes
GET    /api/books/{bookId}/quotes?userId={userId}
GET    /api/quotes/{quoteId}
DELETE /api/quotes/{quoteId}

POST   /api/books/{bookId}/reviews
GET    /api/books/{bookId}/reviews
GET    /api/reviews/{reviewId}
DELETE /api/reviews/{reviewId}

POST   /api/books/{bookId}/sessions
GET    /api/books/{bookId}/sessions?userId={userId}

GET    /api/recommendations?userId={userId}&type=BY_GENRE
GET    /api/recommendations?userId={userId}&type=BY_AUTHOR
GET    /api/recommendations?userId={userId}&type=BY_RATING
GET    /api/recommendations?userId={userId}&type=BY_READING_HISTORY

GET    /api/statistics/users/{userId}/reading
GET    /api/statistics/users/{userId}/genres
GET    /api/statistics/users/{userId}/monthly
```

<h2 align="center">Запуск Локально</h2>

Создать `.env`:

```bash
cp .env.example .env
```

Запустить проект:

```bash
docker compose up --build
```

Локальные адреса:

| Компонент | URL |
|---|---|
| BookNest API | `http://localhost:8080` |
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| PostgreSQL | `localhost:5432` |
| MongoDB | `localhost:27017` |
| Mongo Express | `http://localhost:8081` |
| pgAdmin | `http://localhost:5050` |

Запуск дополнительных инструментов для баз данных:

```bash
docker compose --profile tools up --build
```

<h2 align="center">Примеры Запросов</h2>

Создать пользователя:

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"alex","email":"alex@example.com"}'
```

Создать автора:

```bash
curl -X POST http://localhost:8080/api/authors \
  -H "Content-Type: application/json" \
  -d '{"name":"James Clear","bio":"Author of Atomic Habits."}'
```

Создать жанр:

```bash
curl -X POST http://localhost:8080/api/genres \
  -H "Content-Type: application/json" \
  -d '{"name":"Self-development"}'
```

Создать книгу:

```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Atomic Habits",
    "description": "A practical book about habits and behavior change.",
    "authorId": 1,
    "genreId": 1,
    "pageCount": 320,
    "publishedYear": 2018
  }'
```

Обновить прогресс чтения:

```bash
curl -X POST http://localhost:8080/api/books/1/progress \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"status":"READING","currentPage":120}'
```

Получить рекомендации:

```bash
curl "http://localhost:8080/api/recommendations?userId=1&type=BY_GENRE"
```

<h2 align="center">Конфигурация</h2>

Основные настройки находятся в `src/main/resources/application.yml`.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/booknest
    username: booknest
    password: booknest
  data:
    mongodb:
      uri: mongodb://booknest:booknest@localhost:27017/booknest?authSource=admin
  jpa:
    hibernate:
      ddl-auto: validate
    open-in-view: false
  flyway:
    enabled: true
    locations: classpath:db/migration
```

<h2 align="center">Тестирование</h2>

Запуск тестов:

```bash
mvn test
```

Сборка проекта:

```bash
mvn clean package
```

В проекте есть unit-тесты для сервисной логики и выбора стратегии рекомендаций. Integration test classes описывают сценарии с PostgreSQL и MongoDB Testcontainers.

<h2 align="center">Особенности Реализации</h2>

- бизнес-логика не хранится в контроллерах;
- JPA Entity и MongoDB Document не возвращаются напрямую из API;
- DTO и mapper-слой отделяют API-контракты от моделей хранения;
- PostgreSQL и MongoDB используются для разных типов данных;
- Flyway управляет SQL-схемой;
- Hibernate только валидирует схему;
- ошибки API возвращаются в едином формате;
- рекомендации реализованы через Strategy Pattern и registry стратегий.

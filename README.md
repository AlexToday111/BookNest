# BookNest

**BookNest** is a Java Spring Boot pet project: a REST API for managing a personal library, reading progress, notes, quotes, reviews, ratings and book recommendations.

The project demonstrates backend development with **PostgreSQL**, **MongoDB**, **Flyway**, **Docker Compose**, **REST API**, **Strategy Pattern**, **JUnit 5** and **Testcontainers**.

## Features

- manage users, authors and genres;
- add, update, delete and search books;
- track reading progress and reading status;
- rate books and calculate average rating;
- store notes, quotes, reviews and reading sessions in MongoDB;
- get recommendations by genre, author, rating and reading history;
- view reading statistics, genre distribution and monthly reading activity.

## Tech Stack

| Category | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3 |
| API | REST |
| SQL storage | PostgreSQL |
| NoSQL storage | MongoDB |
| Migrations | Flyway |
| ORM | Spring Data JPA / Hibernate |
| MongoDB integration | Spring Data MongoDB |
| Validation | Jakarta Validation |
| API docs | Swagger / OpenAPI |
| Testing | JUnit 5, Testcontainers |
| Containers | Docker, Docker Compose |
| Build | Maven |

## Why PostgreSQL And MongoDB

PostgreSQL stores structured data with strict relations:

- users;
- authors;
- genres;
- books;
- reading progress;
- ratings.

MongoDB stores flexible user-generated documents:

- notes;
- quotes;
- reviews;
- reading sessions.

Example note document:

```json
{
  "id": "note-123",
  "userId": 1,
  "bookId": 42,
  "page": 117,
  "text": "Interesting idea about habits and systems.",
  "tags": ["habits", "psychology"],
  "mood": "thoughtful",
  "createdAt": "2025-04-14T12:30:00"
}
```

## Architecture

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

## Main Modules

- `user` manages application users.
- `author` manages book authors.
- `genre` manages book genres.
- `book` manages library books, search and pagination.
- `progress` tracks reading status and current page.
- `rating` stores user ratings and average book scores.
- `note` stores MongoDB notes.
- `quote` stores MongoDB quotes.
- `review` stores MongoDB reviews.
- `session` stores MongoDB reading sessions.
- `recommendation` implements Strategy Pattern for recommendations.
- `statistics` calculates reading statistics.

## REST API

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

## Local Run

Create environment file:

```bash
cp .env.example .env
```

Start the project:

```bash
docker compose up --build
```

Local URLs:

| Component | URL |
|---|---|
| BookNest API | `http://localhost:8080` |
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| PostgreSQL | `localhost:5432` |
| MongoDB | `localhost:27017` |
| Mongo Express | `http://localhost:8081` |
| pgAdmin | `http://localhost:5050` |

Start optional database tools:

```bash
docker compose --profile tools up --build
```

## Examples

Create a user:

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"alex","email":"alex@example.com"}'
```

Create an author:

```bash
curl -X POST http://localhost:8080/api/authors \
  -H "Content-Type: application/json" \
  -d '{"name":"James Clear","bio":"Author of Atomic Habits."}'
```

Create a genre:

```bash
curl -X POST http://localhost:8080/api/genres \
  -H "Content-Type: application/json" \
  -d '{"name":"Self-development"}'
```

Create a book:

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

Update reading progress:

```bash
curl -X POST http://localhost:8080/api/books/1/progress \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"status":"READING","currentPage":120}'
```

Get recommendations:

```bash
curl "http://localhost:8080/api/recommendations?userId=1&type=BY_GENRE"
```

## Configuration

Main configuration lives in `src/main/resources/application.yml`.

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

## Testing

Run tests:

```bash
mvn test
```

Build package:

```bash
mvn clean package
```

The project contains focused unit tests for service logic and recommendation strategy selection. Integration test classes document PostgreSQL and MongoDB Testcontainers scenarios.

## Implementation Notes

- Business logic is kept out of controllers.
- JPA entities and MongoDB documents are not returned directly from the API.
- DTOs and mapper classes separate API contracts from storage models.
- PostgreSQL and MongoDB are used for different data shapes.
- Flyway owns the SQL schema; Hibernate validates it.
- API errors use one response format.
- Recommendations use Strategy Pattern and a strategy registry.

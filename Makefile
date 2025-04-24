up:
	docker compose up --build

down:
	docker compose down

test:
	mvn test

build:
	mvn clean package

logs:
	docker compose logs -f app

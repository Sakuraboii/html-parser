# html-parser

Приложение которое помогает парсить html старницы

как запускать:

mvn clean

mvn install

docker-compose up-d

Либо же через удобный вам редактор, только обратите внимание что для работы приложения нужная работающая база данных

Эндпоинты:

POST http://localhost:8080/api/v1/text в качечтве параметра принимает url сайта и парсит его

GET http://localhost:8080/api/v1/text/url в качетсве параметра принимает url сайта по которому нужно выполнить поиск в бд

GET http://localhost:8080/api/v1/text/all показывает все записи в бд, только учтите что там пагинация
Простое приложение на основе Spring JDBC.

# Технологии
* Основные:
  * Spring Core
  * Spring JDBC для работы с БД (JdbcTemplate, SimpleJdbcInsert, RowMapper)
  * logback для логгирования. Сконфигурирован ConsoleAppender
  * MarinaDB в качестве БД
  * HikariCP для пула соединений
* Тестирование:
  * H2 для тестового БД
  * JUnit Juster (JUnit5) для тестирования
  * Mockito для мокирования
  * Spring Test для работы Spring в тестах
# Запуск MarinaDB
1) Запустить БД:
    * Либо запустить готовую БД. Прописать URL в ConnectionAccess класссе.
    * Либо:
        1) собрать контейнер с помощью docker-build\Dockerfile
           ```docker build -t marinadb1172-finance-assistant:1.0 .```
        2) запустить с помощью
           ```docker run --rm --name finance-assistant10 -d -p 3306:3306 marinadb1172-finance-assistant:1.0```
# APP
App - выводит в консоль Users,Transactions при поднятом контейнере MarinaDB.
1) Запустить в IDE Run на App
# UserDaoMarinaDBTest
JUnit5-тесты при поднятом контейнере MarinaDB.
# UserDaoEmbeddedDBTest
JUnit5 + Spring - тесты, поднимается БД H2, используются тестовый конфигурационный класс.
# SimpleUserDaoMockDBTest
JUnit5 + Mockito - тесты, без БД.

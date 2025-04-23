Простое приложение на основе JDBC.

# Технологии
* java. sql для доступа к Connection
* logback для логгирования. Сконфигурирован ConsoleAppender
* MarinaDB в качестве БД
# Запуск в IDE
  1) Запустить БД:
     * Либо запустить готовую БД. Прописать URL в ConnectionAccess класссе.
     * Либо:
       1) собрать контейнер с помощью docker-build\Dockerfile
          ```docker build -t marinadb1172-finance-assistant:1.0 .```
       2) запустить с помощью 
       ```docker run --name finance-assistant10 -d -p 3306:3306 marinadb1172-finance-assistant:1.0```
       3) Либо запустить с репо
        ```docker run --name finance-assistant10 -d -p 3306:3306 onfocus0147/marinadb1172-finance-assistant::latest```
  2) Запустить в IDE Run на App

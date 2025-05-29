Testing various tools for connecting and working with the database

1) _on-jdbc_ — самый простой проект, работает на Connection
2) _on-jdbc-pool_ — чуть сложнее, Connection берем из pool-а соединений
3) _on-jdbc-spring_ — проект на spring-jdbc. Вместо Connection используются SimpleJdbcInsert, JdbcTemplate, NamedParameterJdbcTemplate
4) _on-spring-hibernate_ — проект на Spring+Hibernate, с использованием SessionFactory (не стандарт JPA)
5) _on-jpa-hibernate_ — проект на Spring+Hibernate, с использованием EntityManager (стандарт JPA)

Подробнее о проектах - внутри их readme-файлов.
Некоторые проекты можно запустить "как приложение", в других можно запустить только Junit тесты.
Модель БД в проектах имеет общий смысл, но отличается (эволюционировала).

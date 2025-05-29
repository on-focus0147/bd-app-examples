Простое приложение на основе Spring + Hibernate, отвечающее стандарту JPA

# Технологии
* Основные:
    * Spring (spring-context, spring-orm)
    * Hibernate 7.0.0.Final
    * HikariCP для пула соединений
    * Hibernate ORM Hibernate Processor (Hibernate Jpamodelgen) 
      * для генерации классов метамодели (JPA)
      * сгенерированные классы обеспечивают статический доступ к метаданным entity
* Тестирование:
    * MarinaDB для тестового БД
    * JUnit Jupiter (JUnit5) для тестирования
    * JUnit Testcontainers запускать БД в контейнере при тестировании
    * Spring Test для работы Spring в тестах
# Запуск
Написаны JUnit тесты. 
Перед запуском нужно включить docker.
Перед запуском в IDE нужно сгенерировать классы метамодели
В Idea для этого:
* Build->Настройки->Compiler->Annotation Processor -> Enable.
* Выполнить Build

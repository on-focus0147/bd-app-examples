package on.focus0147.repositories;

import on.focus0147.entities.User;

import java.util.List;
/**
 * Этот интерфейс предоставляет метод для фильтрации пользователей на основе различных критериев,
 * таких как имя, email и параметры сортировки, используя JPA Criteria API для типобезопасных запросов.
 */
public interface UserCriteriaRepository {
    /**
     * Получить список пользователей ({@link User}) на основе переданных фильтров и параметров сортировки.
     *
     * @param name      имя пользователя для фильтрации (опционально).
     * @param email     email пользователя для фильтрации (опционально).
     * @param sortBy    колонка таблиц для сортировки
     * @param ascending если true, то asc. Иначе desc.
     */
    List<User> getFilteredUsers(String name, String email, String sortBy, boolean ascending);
}

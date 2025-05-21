package on.focus0147.dao;

import on.focus0147.dao.pojo.User;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Set<User> findAll();
    void add(User user);
    Optional<Integer> addWithGeneratedId(User user);
    void dropById(User user);
}

package on.focus0147.dao;

import on.focus0147.dao.pojo.User;

import java.util.Set;

public interface UserDao {
    Set<User> findAll();
}

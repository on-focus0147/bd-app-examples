package on.focus0147.repositories;

import on.focus0147.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    //select
    List<User> findAll();
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
    Optional<User> findWithDependenciesByNamedQuery(Integer id);
    Optional<User> findWithDependenciesByHibernate(Integer id);
    //insert
    User add(User user);
    //update
    User update(User user);
    //delete
    void dropById(Integer id);

}

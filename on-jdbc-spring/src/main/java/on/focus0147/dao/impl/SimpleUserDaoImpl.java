package on.focus0147.dao.impl;

import on.focus0147.dao.UserDao;
import on.focus0147.dao.pojo.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component("userDao")
public class SimpleUserDaoImpl implements UserDao, InitializingBean {
    private static final String TABLE_NAME = "USERS";
    private static final String KEY_COLUMN = "ID";

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public SimpleUserDaoImpl(SimpleJdbcInsert simpleJdbcInsert){
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public void afterPropertiesSet() {
        simpleJdbcInsert
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(KEY_COLUMN);
    }

    @Override
    public Set<User> findAll() {
        throw new UnsupportedOperationException("Не реализовано!");
    }

    @Override
    public void add(User user) {
        simpleJdbcInsert.execute(user.toMap());
    }

    @Override
    public Optional<Integer> addWithGeneratedId(User user) {
        Number generatedKey = simpleJdbcInsert.executeAndReturnKey(user.toMap());
        return (null == generatedKey) ? Optional.empty() : Optional.of(generatedKey.intValue());
    }

    @Override
    public void dropById(User user) {
        throw new UnsupportedOperationException("Не реализовано!");
    }

}

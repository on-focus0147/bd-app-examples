package on.focus0147.dao.impl;

import on.focus0147.dao.TransactionDao;
import on.focus0147.dao.UserDao;
import on.focus0147.dao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Component("userDao")
public class UserDaoImpl implements UserDao {
    private final TransactionDao transactionDao;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDaoImpl(TransactionDao transactionDao,
                NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.transactionDao = transactionDao;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Set<User> findAll() {
        return new LinkedHashSet<>(namedParameterJdbcTemplate.query("select U.ID" +
                ", U.NAME" +
                ", U.EMAIL" +
                ", U.HASHED_PASSWORD " +
                "from USERS U"
                , (rs, _) -> new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("HASHED_PASSWORD"),
                        transactionDao.findByUserId(rs.getInt("id"))
                )
        ));
    }

    @Override
    public void add(User user) {
        namedParameterJdbcTemplate.update("INSERT INTO USERS " +
                "(ID, NAME, EMAIL, HASHED_PASSWORD) VALUES" +
                "(:id, :name, :email, :hashedPassword)",
                new BeanPropertySqlParameterSource(user));
    }

    /**
     * @deprecated Использована БД-зависимая функция LAST_INSERT_ID().
     */
    @Deprecated(since = "Использована БД-зависимая функция LAST_INSERT_ID().")
    @Override
    public Optional<Integer> addWithGeneratedId(User user) {
        namedParameterJdbcTemplate.update("INSERT INTO USERS " +
                        "(NAME, EMAIL, HASHED_PASSWORD) VALUES" +
                        "(:name, :email, :hashedPassword)",
                new BeanPropertySqlParameterSource(user));
        Integer generatedKey = namedParameterJdbcTemplate.getJdbcTemplate()
                .queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        return (null == generatedKey) ? Optional.empty() : Optional.of(generatedKey);
    }

    @Override
    public void dropById(User user) {
        namedParameterJdbcTemplate.update("DELETE FROM USERS WHERE ID = :id",
                new BeanPropertySqlParameterSource(user));
        //Тут должно быть удаление транзакций
    }
}

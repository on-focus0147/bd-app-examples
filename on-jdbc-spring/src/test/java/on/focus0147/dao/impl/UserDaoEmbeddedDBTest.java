package on.focus0147.dao.impl;

import on.focus0147.configuration.TestConfiguration;
import on.focus0147.dao.UserDao;
import on.focus0147.dao.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql({ "classpath:h2/dropTable.sql", "classpath:h2/createTable.sql" })
@SpringJUnitConfig(classes = {TestConfiguration.class})
class UserDaoEmbeddedDBTest {

    @Autowired
    UserDao userDao;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    @SqlGroup({
            @Sql(value = "classpath:h2/InsertData.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:h2/DeleteData.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    void findAllTest() {
        Set<User> users = userDao.findAll();
        assertEquals(2, users.size());
    }

    @Test
    void addTest() {
        User user = new User(1, "Simple1", "simple1@example.com",
                "hashedPassword1", null);
        userDao.add(user);
        Integer id = jdbcTemplate.queryForObject("select U.ID from USERS U " +
                        "WHERE U.NAME = ? AND U.EMAIL = ? AND U.HASHED_PASSWORD = ?",
                Integer.class, user.name(), user.email(),user.hashedPassword());
        assertEquals(user.id(), id);
    }

    @Test
    @SqlGroup({
            @Sql(statements = "INSERT INTO USERS (ID, NAME, EMAIL, HASHED_PASSWORD) " +
                    "VALUES (1, 'Alex Smith', 'alex.smith@example.com', 'hashedPassword1')"
                    , executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:h2/DeleteData.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    void dropByIdTest(){
        User user = new User(1, null,null,null, null);
        userDao.dropById(user);
        Integer isExist = jdbcTemplate.queryForObject("select count(U.ID) from USERS U WHERE U.ID = ?",
                Integer.class, user.id());
        assertEquals(0, isExist);
    }


}
package on.focus0147.dao.impl;

import on.focus0147.dao.pojo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class SimpleUserDaoMockDBTest {

    @Mock
    SimpleJdbcInsert simpleJdbcInsert;

    @InjectMocks
    SimpleUserDaoImpl userDao;

    @Test
    void addTest(){
        User user = new User(1, "Simple1", "simple1@example.com",
                "hashedPassword1", null);
        Map<String, Object> parameters = user.toMap();
        userDao.add(user);
        Mockito.verify(simpleJdbcInsert, Mockito.times(1)).execute(parameters);
    }

    @Test
    void addWithGeneratedIdTest(){
        User user = new User(null, "Simple1", "simple1@example.com",
                "hashedPassword1", null);
        Map<String, Object> parameters = user.toMap();
        userDao.addWithGeneratedId(user);
        Mockito.verify(simpleJdbcInsert, Mockito.times(1)).executeAndReturnKey(parameters);
    }

    @Test
    void findAllTest(){
        Assertions.assertThrows(UnsupportedOperationException.class, () -> userDao.findAll());
    }

    @Test
    void dropByIdTest(){
        Assertions.assertThrows(UnsupportedOperationException.class, () -> userDao.dropById(null));
    }

}

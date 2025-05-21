package on.focus0147.dao.impl;

import on.focus0147.configuration.DataSourceConfiguration;
import on.focus0147.dao.pojo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoMarinaDBTest {

    @Disabled("Нужна MariaDB, закомментить, если контейнер поднят")
    @Test
    void findAll() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);
        UserDaoImpl ud = context.getBean("userDao", UserDaoImpl.class);
        Set<User> users = ud.findAll();
        assertEquals(10, users.size());

    }

    @Disabled("Нужна MariaDB, закомментить, если контейнер поднят")
    @Test
    void addWithGeneratedIdTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);
        UserDaoImpl ud = context.getBean("userDao", UserDaoImpl.class);

        User user = new User(null, "Simple1", "simple1@example.com",
                "hashedPassword1", null);
        Optional<Integer> id = ud.addWithGeneratedId(user);
        Assertions.assertFalse(id.isEmpty());
    }
}
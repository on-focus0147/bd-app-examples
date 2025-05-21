package on.focus0147.services.impl;

import on.focus0147.configuration.ApplicationConfiguration;
import on.focus0147.configuration.TestContainersBase;
import on.focus0147.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringJUnitConfig(ApplicationConfiguration.class)
class UserServiceImplTest extends TestContainersBase {

    @Autowired
    UserService userService;

    @Test
    void testCheckPassword() {
        boolean check = userService.checkPassword("alex.smith@example.com", "password1");
        Assertions.assertTrue(check);
    }
}
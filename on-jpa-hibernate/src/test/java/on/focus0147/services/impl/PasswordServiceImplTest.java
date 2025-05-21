package on.focus0147.services.impl;

import on.focus0147.configuration.ApplicationConfiguration;
import on.focus0147.configuration.TestContainersBase;
import on.focus0147.services.PasswordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringJUnitConfig(ApplicationConfiguration.class)
class PasswordServiceImplTest extends TestContainersBase {

    @Autowired
    PasswordService passwordService;

    @Test
    void encrypt() {
        String password = "password1";
        String encrypted = passwordService.encrypt(password);
        Assertions.assertNotEquals(password, encrypted);

    }

    @Test
    void decrypt() {
        String password = "password1";
        String enc = "+zmKNX2kafRK6M2VZNxM+cVPCZfxQJ6r";
        String encrypted = passwordService.decrypt(enc);
        Assertions.assertEquals(password, encrypted);
    }

    @Test
    void checkEquals() {
        String password = "password1";
        String enc = "+zmKNX2kafRK6M2VZNxM+cVPCZfxQJ6r";
        passwordService.checkEquals(password,enc);
    }
}
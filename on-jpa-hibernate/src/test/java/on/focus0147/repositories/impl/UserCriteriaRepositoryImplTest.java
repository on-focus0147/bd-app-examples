package on.focus0147.repositories.impl;

import on.focus0147.configuration.ApplicationConfiguration;
import on.focus0147.configuration.TestContainersBase;
import on.focus0147.entities.User;
import on.focus0147.repositories.UserCriteriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

@Testcontainers
@SpringJUnitConfig(ApplicationConfiguration.class)
class UserCriteriaRepositoryImplTest  extends TestContainersBase {

    @Autowired
    private UserCriteriaRepository userCriteriaRepository;

    @Test
    void getFilteredUsersName() {
        List<User> users = userCriteriaRepository.getFilteredUsers("Alex Smith",
                null, null, false);
        Assertions.assertNotNull(users);
        Assertions.assertEquals(1, users.size());
        checkFullAlex(Optional.of(users.getFirst()));
    }

    @Test
    void getFilteredEmail() {
        List<User> users = userCriteriaRepository.getFilteredUsers(null,
                "alex.smith@example.com", null, true);
        Assertions.assertNotNull(users);
        Assertions.assertEquals(1, users.size());
        checkFullAlex(Optional.of(users.getFirst()));
    }

    @Test
    void getFilteredNone() {
        List<User> users = userCriteriaRepository.getFilteredUsers(null,
                null, null, true);
        Assertions.assertNotNull(users);
        Assertions.assertEquals(0, users.size());
    }

    @Test
    void getFilteredSort() {
        List<User> users = userCriteriaRepository.getFilteredUsers(null,
                null, "id", false);
        Assertions.assertNotNull(users);
        User user = users.getFirst();
        Assertions.assertEquals("Chloe Kim", user.getName());
    }
}
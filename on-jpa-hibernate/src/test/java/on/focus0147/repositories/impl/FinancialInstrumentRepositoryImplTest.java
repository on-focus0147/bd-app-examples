package on.focus0147.repositories.impl;

import on.focus0147.configuration.ApplicationConfiguration;
import on.focus0147.configuration.TestContainersBase;
import on.focus0147.entities.FinancialInstrument;
import on.focus0147.entities.User;
import on.focus0147.repositories.FinancialInstrumentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.Set;

@Testcontainers
@SpringJUnitConfig(ApplicationConfiguration.class)
class FinancialInstrumentRepositoryImplTest extends TestContainersBase {

    @Autowired
    FinancialInstrumentRepository repository;

    @Test
    void testFindByIdWithUsers() {
        Optional<FinancialInstrument> financialInstrumentOptional = repository.findByIdWithUsers(1);
        Assertions.assertTrue(financialInstrumentOptional.isPresent());
        Set<User> userSet = financialInstrumentOptional.get().getUsers();
        Assertions.assertNotNull(userSet);
        Assertions.assertEquals(4,userSet.size());
    }

    @Test
    void testFindByIdWithUsersNotExist() {
        Optional<FinancialInstrument> financialInstrumentOptional = repository.findByIdWithUsers(100);
        Assertions.assertTrue(financialInstrumentOptional.isEmpty());
    }

    @Test
    void testFindByIdWithUsersNull() {
        Assertions.assertThrows(NullPointerException.class,
                ()->  repository.findByIdWithUsers(null));
    }
}
package on.focus0147.repositories.impl;

import on.focus0147.configuration.HibernateConfiguration;
import on.focus0147.configuration.TestContainersBase;
import on.focus0147.entities.FinancialInstrument;
import on.focus0147.entities.User;
import on.focus0147.repositories.FinancialInstrumentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;

@Testcontainers
@SpringJUnitConfig(HibernateConfiguration.class)
class FinancialInstrumentRepositoryImplTest extends TestContainersBase {

    @Autowired
    FinancialInstrumentRepository repository;

    @Test
    void testFindByIdWithUsers() {
        FinancialInstrument financialInstrument = repository.findByIdWithUsers(1);
        Assertions.assertNotNull(financialInstrument);
        Set<User> userSet = financialInstrument.getUsers();
        Assertions.assertNotNull(userSet);
        Assertions.assertEquals(4,userSet.size());
    }
}
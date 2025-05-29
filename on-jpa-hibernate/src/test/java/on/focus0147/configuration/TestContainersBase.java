package on.focus0147.configuration;

import on.focus0147.entities.Payment;
import on.focus0147.entities.User;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TestContainersBase {
    @Container
    static MariaDBContainer<?> dbContainer = new MariaDBContainer<>("mariadb:latest")
            .withInitScripts(List.of("init/createTable.sql", "init/insertData.sql"));

    @DynamicPropertySource
    static void setUp(DynamicPropertyRegistry registry) {
        registry.add("dataSource.driverClassName", dbContainer::getDriverClassName);
        registry.add("dataSource.url", dbContainer::getJdbcUrl);
        registry.add("dataSource.username", dbContainer::getUsername);
        registry.add("dataSource.password", dbContainer::getPassword);
        registry.add("dataSource.dbname", dbContainer::getDatabaseName);
        System.out.println("CURRENT URL : " + dbContainer.getJdbcUrl());
    }

    protected void checkFullAlex(Optional<User> optionalUser){
        User user = checkAlex(optionalUser);
        Assertions.assertNotNull(user.getPayments());
        Assertions.assertEquals(2 , user.getPayments().size());
        Assertions.assertNotNull(user.getFinancialInstruments());
        Assertions.assertEquals(2, user.getFinancialInstruments().size());
    }

    protected void checkLazyAlex(Optional<User> optionalUser){
        User user = checkAlex(optionalUser);
        Set<Payment> paymentSet = user.getPayments();
        Assertions.assertNotNull(paymentSet);//PersistentBag
        Assertions.assertThrows(LazyInitializationException.class, paymentSet::size);
    }

    protected User checkAlex(Optional<User> optionalUser){
        Assertions.assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        Assertions.assertEquals("Alex Smith", user.getName());
        Assertions.assertEquals("alex.smith@example.com", user.getEmail());
        Assertions.assertEquals("+zmKNX2kafRK6M2VZNxM+cVPCZfxQJ6r", user.getEncryptedPassword());
        return user;
    }
}

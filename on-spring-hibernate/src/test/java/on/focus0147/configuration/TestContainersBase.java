package on.focus0147.configuration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;

public class TestContainersBase {
    @Container
    static MariaDBContainer<?> dbContainer = new MariaDBContainer<>("mariadb:latest")
            .withInitScripts(List.of("init/createTable.sql", "init/insertData.sql"));

    @DynamicPropertySource // this does the magic
    static void setUp(DynamicPropertyRegistry registry) {
        registry.add("dataSource.driverClassName", dbContainer::getDriverClassName);
        registry.add("dataSource.url", dbContainer::getJdbcUrl);
        registry.add("dataSource.username", dbContainer::getUsername);
        registry.add("dataSource.password", dbContainer::getPassword);
        registry.add("dataSource.dbname", dbContainer::getDatabaseName);
        System.out.println("URL" + dbContainer.getJdbcUrl());
        System.out.println("UusernameRL" + dbContainer.getUsername());
        System.out.println("pas" + dbContainer.getPassword());
        System.out.println("db" + dbContainer.getDatabaseName());
    }
}

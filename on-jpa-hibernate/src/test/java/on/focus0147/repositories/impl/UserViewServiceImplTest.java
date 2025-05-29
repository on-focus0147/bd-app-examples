package on.focus0147.repositories.impl;

import on.focus0147.configuration.ApplicationConfiguration;
import on.focus0147.configuration.TestContainersBase;
import on.focus0147.repositories.UserViewService;
import on.focus0147.view.UserUsdView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@SpringJUnitConfig(ApplicationConfiguration.class)
class UserViewServiceImplTest  extends TestContainersBase {

    @Autowired
    UserViewService userViewService;

    @Test
    void findAll() {
        List<UserUsdView> list = userViewService.findAll();
        Assertions.assertEquals(3, list.size());
    }
}
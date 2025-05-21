package on.focus0147.configuration;

import on.focus0147.dao.impl.SimpleUserDaoImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Configuration
@Import(EmbeddedDBConfiguration.class)
@ComponentScan(basePackages = "on.focus0147.dao.impl",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                value = SimpleUserDaoImpl.class)
)
public class TestConfiguration {
}

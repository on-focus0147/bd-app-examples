package on.focus0147.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Import(TemplateConfiguration.class)
public class EmbeddedDBConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedDBConfiguration.class);

    @Bean
    DataSource dataSource(){
        try {
            var dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2).build();
        } catch (Exception e) {
            LOGGER.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }
}

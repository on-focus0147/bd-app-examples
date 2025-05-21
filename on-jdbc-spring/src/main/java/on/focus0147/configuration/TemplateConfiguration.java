package on.focus0147.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@Configuration
public class TemplateConfiguration {

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    @Scope(scopeName = "prototype")
    SimpleJdbcInsert simpleJdbcInsert(DataSource dataSource){
        return new SimpleJdbcInsert(dataSource);
    }
}

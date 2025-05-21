package on.focus0147.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource/jdbc.properties")
@ComponentScan("on.focus0147.dao.impl")
@Import(TemplateConfiguration.class)
public class DataSourceConfiguration {

    @Value("${dataSource.driverClassName}")
    String driverName;
    @Value("${dataSource.url}")
    String url;
    @Value("${dataSource.username}")
    String userName;
    @Value("${dataSource.password}")
    String password;
    @Value("${dataSource.maxPoolSize}")
    Integer maxPoolSize;
    @Value("${dataSource.connectionTimeout}")
    Integer connectionTimeout;

    @Bean(destroyMethod = "close")
    DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverName);
        config.setJdbcUrl(url);
        config.setUsername(userName);
        config.setPassword(password);
        config.setMaximumPoolSize(maxPoolSize);
        config.setConnectionTimeout(connectionTimeout);
        return new HikariDataSource(config);
    }
}

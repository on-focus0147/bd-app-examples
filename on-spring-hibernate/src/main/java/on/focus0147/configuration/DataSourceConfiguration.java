package on.focus0147.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource/jdbc.properties")
@ComponentScan("on.focus0147.dao.impl")
public class DataSourceConfiguration {

    @Value("${dataSource.driverClassName}")
    private  String driverName;
    @Value("${dataSource.url}")
    private  String url;
    @Value("${dataSource.username}")
    private  String userName;
    @Value("${dataSource.password}")
    private String password;
    @Value("${dataSource.maxPoolSize}")
    private Integer maxPoolSize;

    @Bean(destroyMethod = "close")
    DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverName);
        config.setJdbcUrl(url);
        config.setUsername(userName);
        config.setPassword(password);
        config.setMaximumPoolSize(maxPoolSize);
        return new HikariDataSource(config);
    }
}

package on.focus0147.configuration;

import org.hibernate.cfg.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"on.focus0147.repositories"})
@EnableTransactionManagement
@Import(DataSourceConfiguration.class)
public class HibernateConfiguration {

    private final DataSource dataSource;

    @Autowired
    HibernateConfiguration(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put(SchemaToolingSettings.HBM2DDL_AUTO, "none");
        hibernateProp.put(JdbcSettings.FORMAT_SQL, true);
        hibernateProp.put(JdbcSettings.USE_SQL_COMMENTS, true);
        hibernateProp.put(JdbcSettings.SHOW_SQL, true);
        //“depth” for outer joins when the mapping objects have associations with other mapped objects.
        hibernateProp.put(FetchSettings.MAX_FETCH_DEPTH, 4);
        hibernateProp.put(BatchSettings.STATEMENT_BATCH_SIZE, 10);
        hibernateProp.put(JdbcSettings.STATEMENT_FETCH_SIZE, 50);
        hibernateProp.put(TransactionSettings.JTA_PLATFORM, "org.springframework.orm.hibernate5.ConfigurableJtaPlatform");
        return hibernateProp;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("on.focus0147.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        var transactionManager =  new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}

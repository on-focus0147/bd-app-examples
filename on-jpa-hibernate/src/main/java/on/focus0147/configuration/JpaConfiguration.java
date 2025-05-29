package on.focus0147.configuration;

import org.hibernate.cfg.JdbcSettings;
import org.hibernate.cfg.SchemaToolingSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"on.focus0147.repositories"})
@EnableTransactionManagement
@Import(DataSourceConfiguration.class)
public class JpaConfiguration {

    private final DataSource dataSource;

    @Autowired
    JpaConfiguration(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public Properties jpaProperties() {
        Properties jpaProps = new Properties();
        jpaProps.put(SchemaToolingSettings.HBM2DDL_AUTO, "none");
        jpaProps.put(JdbcSettings.FORMAT_SQL, true);
        jpaProps.put(JdbcSettings.USE_SQL_COMMENTS, true);
        jpaProps.put(JdbcSettings.SHOW_SQL, true);
        return jpaProps;
    }

    @Bean
    public AbstractEntityManagerFactoryBean entityManagerFactory() {
        var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("on.focus0147.entities");
        factory.setDataSource(dataSource);
        factory.setJpaProperties(jpaProperties());
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(AbstractEntityManagerFactoryBean entityManagerFactoryBean) {
        var transactionManager =  new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

}

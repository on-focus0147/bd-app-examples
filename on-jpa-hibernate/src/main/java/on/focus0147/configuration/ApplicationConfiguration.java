package on.focus0147.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HibernateConfiguration.class})
@ComponentScan(basePackages = {"on.focus0147.services"})
public class ApplicationConfiguration {
}

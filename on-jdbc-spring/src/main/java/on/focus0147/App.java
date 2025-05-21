package on.focus0147;


import on.focus0147.configuration.DataSourceConfiguration;
import on.focus0147.dao.UserDao;
import on.focus0147.dao.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);
        UserDao ud = context.getBean("userDao", UserDao.class);
        printResult(ud);
    }

    static void printResult(UserDao userDao){
        for(User user : userDao.findAll()){
            LOGGER.info("USER : {}" ,user.name());
            user.transactions()
                    .forEach(transaction ->
                            LOGGER.info("\t TRANSACTIONS : {} {}" , transaction.transactionType(),
                                    transaction.amount()));
        }
    }
}

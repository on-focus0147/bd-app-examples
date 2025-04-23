package on.focus0147;

import on.focus0147.dao.TransactionDao;
import on.focus0147.dao.UserDao;
import on.focus0147.dao.impl.TransactionDaoImpl;
import on.focus0147.dao.impl.UserDaoImpl;
import on.focus0147.dao.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        TransactionDao transactionDao = new TransactionDaoImpl();
        UserDao userDao = new UserDaoImpl();

        for(User user : userDao.findAll()){
            LOGGER.info("USER {}" ,user.name());
            transactionDao
                    .findByUserId(user.id())
                    .forEach(transaction ->
                            LOGGER.info("\t TRANSACTIONS : {} {}" , transaction.transactionType(),
                                    transaction.amount()));
        }
    }
}

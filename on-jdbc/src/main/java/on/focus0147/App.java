package on.focus0147;

import on.focus0147.dao.TransactionDao;
import on.focus0147.dao.UserDao;
import on.focus0147.dao.impl.TransactionDaoImpl;
import on.focus0147.dao.impl.UserDaoImpl;
import on.focus0147.dao.pojo.User;

public class App {


    public static void main( String[] args ) {
        System.out.println("*************************************");

        TransactionDao transactionDao = new TransactionDaoImpl();
        UserDao userDao = new UserDaoImpl();

        for(User user : userDao.findAll()){
            System.out.println(user.toString());
            transactionDao
                    .findByUserId(user.id())
                    .forEach(transaction ->
                    System.out.println("\t" + transaction.toString()));
        }
    }
}

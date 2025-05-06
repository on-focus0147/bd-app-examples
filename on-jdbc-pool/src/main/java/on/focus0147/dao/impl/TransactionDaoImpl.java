package on.focus0147.dao.impl;

import on.focus0147.dao.ConnectionAccess;
import on.focus0147.dao.TransactionDao;
import on.focus0147.dao.pojo.Transaction;
import on.focus0147.dao.pojo.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDaoImpl.class);

    @Override
    public List<Transaction> findByUserId(int userId) {
        List<Transaction> result = new ArrayList<>();
        try(var connection = ConnectionAccess.getConnection();
            var statement = connection.prepareStatement("SELECT T.ID" +
                    ", T.USER_ID" +
                    ", T.TRANSACTION_DATE" +
                    ", T.AMOUNT" +
                    ", T.CURRENCY" +
                    ", T.TRANSACTION_TYPE " +
                    "FROM TRANSACTION T WHERE USER_ID=?")
        ){statement.setInt(1, userId);
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getInt("ID"),
                        resultSet.getInt("USER_ID"),
                        resultSet.getDate("TRANSACTION_DATE"),
                        resultSet.getLong("AMOUNT"),
                        TransactionType.valueOf(resultSet.getString("TRANSACTION_TYPE"))
                );
                result.add(transaction);
            }
        } catch (SQLException e){

            LOGGER.error(e.getMessage());
        }
        return result;
    }
}

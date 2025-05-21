package on.focus0147.dao.impl;

import on.focus0147.dao.TransactionDao;
import on.focus0147.dao.pojo.Transaction;
import on.focus0147.dao.pojo.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("transactionDao")
public class TransactionDaoImpl implements TransactionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transaction> findByUserId(int userId) {
        return jdbcTemplate.query("SELECT T.ID" +
                        ", T.USER_ID" +
                        ", T.TRANSACTION_DATE" +
                        ", T.AMOUNT" +
                        ", T.CURRENCY" +
                        ", T.TRANSACTION_TYPE " +
                        "FROM TRANSACTIONS T WHERE USER_ID=?",
                new TransactionRowMapper(),
                userId
                );
    }

    private static class TransactionRowMapper implements RowMapper<Transaction> {

        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Transaction(
                    rs.getInt("ID"),
                    rs.getInt("USER_ID"),
                    rs.getDate("TRANSACTION_DATE"),
                    rs.getLong("AMOUNT"),
                    TransactionType.valueOf(rs.getString("TRANSACTION_TYPE"))
            );
        }
    }
}

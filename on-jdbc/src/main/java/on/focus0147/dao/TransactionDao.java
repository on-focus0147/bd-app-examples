package on.focus0147.dao;

import on.focus0147.dao.pojo.Transaction;

import java.util.List;

public interface TransactionDao {
    List<Transaction> findByUserId(int userId);
}

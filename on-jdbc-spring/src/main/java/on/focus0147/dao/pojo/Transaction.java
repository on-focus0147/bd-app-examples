package on.focus0147.dao.pojo;

import java.util.Date;

public record Transaction(Integer id,
                          Integer userId,
                          Date transactionDate,
                          Long amount,
                          TransactionType transactionType) {
}

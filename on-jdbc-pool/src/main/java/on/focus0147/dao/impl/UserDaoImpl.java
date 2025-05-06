package on.focus0147.dao.impl;

import on.focus0147.dao.ConnectionAccess;
import on.focus0147.dao.UserDao;
import on.focus0147.dao.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    @Override
    public Set<User> findAll() {
        Set<User> result = new HashSet<>();
        try (var connection = ConnectionAccess.getConnection();
             var statement = connection.prepareStatement("select U.ID" +
                     ", U.NAME" +
                     ", U.EMAIL" +
                     ", U.HASHED_PASSWORD " +
                     "from USER U");
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User singer = new User(resultSet.getInt("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("HASHED_PASSWORD"));
                result.add(singer);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }
}

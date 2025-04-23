package on.focus0147.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionAccess {
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/mydb?useSSL=false",
                "myuser",
                "mypassword");
    }
}

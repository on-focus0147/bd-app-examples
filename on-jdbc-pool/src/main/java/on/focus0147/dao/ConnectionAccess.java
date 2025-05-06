package on.focus0147.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionAccess {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/mydb");
        config.setUsername("myuser");
        config.setPassword("mypassword");
        config.setDriverClassName("org.mariadb.jdbc.Driver");

        // Настройки пула соединений
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private ConnectionAccess(){}

    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}

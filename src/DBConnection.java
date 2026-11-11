
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/project_management";
        String user = "root";
        String password = System.getenv("DB_PASSWORD");

        if (password == null || password.isEmpty()) {
            throw new SQLException("DB_PASSWORD environment variable is not set.");
        }

        return DriverManager.getConnection(url, user, password);
    }
}

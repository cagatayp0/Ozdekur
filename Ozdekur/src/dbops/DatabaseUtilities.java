package dbops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtilities {

    public static String url = "jdbc:mysql://localhost:3306/ozdekur";
    public static String user = "root";
    public static String password = "password";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}

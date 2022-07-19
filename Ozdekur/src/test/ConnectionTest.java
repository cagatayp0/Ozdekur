package test;

import java.sql.*;

public class ConnectionTest {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ozdekur";
        String user = "root";
        String password = "password";
        Connection connection = DriverManager.getConnection(url, user, password);        
        System.out.println(connection.getMetaData().getDatabaseProductName());        
        connection.close();
    }
}

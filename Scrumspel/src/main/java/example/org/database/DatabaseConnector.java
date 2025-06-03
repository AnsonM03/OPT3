package example.org.database;

import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ScrumSpel";
    private static final String USER = "root";
    private static final String PASSWORD = "Root1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}



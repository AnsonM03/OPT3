package org.example;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/scrumspel";
    private static final String USER = "root";
    private static final String PASSWORD = "Root1234";

    public static void loadSQLTable(String tableName) {
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Succesvol verbonden!!");

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + ": " + rs.getString(i) + " | ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("❌ SQL-fout: " + e.getMessage());
        }
    }



    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void InsertSQLScript(String query) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {




        } catch (SQLException e) {
            System.err.println("❌ SQL-fout: " + e.getMessage());
        }
    }
}



package example.org.database;

import java.sql.*;

public class SQLLoader {
    public static void loadFromDatabase(String tableName) {
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = DatabaseConnector.getConnection();
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

//    dit is om een specifieke column te laden.
//    zo wordt het gebruikt => SQLLoader.loadFromDatabase(Arrays.asList("id", "naam"), "klanten");

    public static void loadFromDatabase(String columns, String tableName) {
        String columnString = String.join(", ", columns);
        String query = "SELECT " + columnString + " FROM " + tableName;

        try (Connection conn = DatabaseConnector.getConnection();
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
}

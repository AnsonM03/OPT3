package example.org;

import java.util.*;
import java.sql.*;
import java.util.ArrayList;

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

    public static <T> T loadById(int id, Mapper<T> mapper) {
        String query = "SELECT * FROM " + mapper.getTableName() + " WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapper.mapRow(rs);
            } else {
                System.out.println("⚠️ Geen resultaat gevonden met ID: " + id);
                return null;
            }

        } catch (SQLException e) {
            System.err.println("❌ SQL-fout bij loadById: " + e.getMessage());
            return null;
        }
    }


    public static <T> List<T> loadAll(String tableName, Mapper<T> mapper) {
        List<T> result = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                T item = mapper.mapRow(rs);
                System.out.println("Gelezen item: " + item);
                result.add(item);
            }


        } catch (SQLException e) {
            System.err.println("❌ SQL-fout: " + e.getMessage());
        }

        return result;
    }
}

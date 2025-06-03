package example.org.database;

import example.org.Templates.SQLSavable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SQLSaver {
    public static void saveToDatabase(SQLSavable object) {
        String tableName = object.getTableName();
        List<String> columns = object.getColumnNames();
        List<Object> values = object.getValues();

        if (columns.size() != values.size()) {
            throw new IllegalArgumentException("Aantal kolommen en waarden komt niet overeen.");
        }


        String columnString = String.join(", ", columns);
        String placeholders = String.join(", ", Collections.nCopies(values.size(), "?"));

        String query = "INSERT INTO " + tableName + " (" + columnString + ") VALUES (" + placeholders + ")";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < values.size(); i++) {
                stmt.setObject(i + 1, values.get(i));
            }

            stmt.executeUpdate();
            System.out.println("✅ Gegevens succesvol opgeslagen in tabel: " + tableName);

        } catch (SQLException e) {
            System.err.println("❌ Fout bij opslaan: " + e.getMessage());
        }
    }
}

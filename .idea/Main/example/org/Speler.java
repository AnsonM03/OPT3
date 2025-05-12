package example.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Speler {
    private String naam;
    private int huidigeKamer;
    private int hp;

    public Speler(String naam, int hp) {
        this.naam = naam;
        this.hp = hp;
        this.huidigeKamer = 0;
    }

    public void neemSchade(int schade){
        this.hp -= schade;
        if(this.hp < 0){
            this.hp = 0;
        }
    }

    public void toonStatus() {
        System.out.println("Speler: " + naam);
        System.out.println("Huidige kamer: " + huidigeKamer);
        System.out.println("HP: " + hp);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHuidigeKamer() {
        return huidigeKamer;
    }

    public void setHuidigeKamer(int kamer) {
        this.huidigeKamer = kamer;
    }
public void saveToDatabase() {
    String query = """
            INSERT INTO speler (naam, hp, kamer) 
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE
            hp = VALUES(hp),
            kamer = VALUES(kamer);
            """;

    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, naam);
        stmt.setInt(2, hp);
        stmt.setInt(3, huidigeKamer);
        stmt.executeUpdate();
        System.out.println("Speler opgeslagen in de database");
    } catch (SQLException e) {
        System.out.println("Fout bij het opslaan speler: " + e.getMessage());
    }
}

public void loadFromDatabase() {
    String query = "SELECT hp, kamer FROM speler WHERE naam = ?";

    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, naam);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            this.hp = rs.getInt("hp");
            this.huidigeKamer = rs.getInt("kamer");
            System.out.println("Speler voortgang geladen");
        } else {
            System.out.println("Geen bestaande voortgang gevonden");
        }
    } catch (SQLException e) {
        System.out.println("Fout bij laden speler: " + e.getMessage());
    }
}

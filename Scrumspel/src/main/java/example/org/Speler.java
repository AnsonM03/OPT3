package example.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Speler implements SQLSavable{
    private String naam;
    private int huidigeKamer;
    private int hp;

    public Speler(String naam, int hp) {
        this.naam = naam;
        this.hp = hp;
        this.huidigeKamer = 0;
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

    @Override
    public String getTableName() {
        return "speler";
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("naam", "hp", "kamer");
    }

    @Override
    public List<Object> getValues() {
        return Arrays.asList(naam, hp, huidigeKamer);
    }

    @Override
    public String toString() {
        return "Speler{naam='" + naam + "', hp= "+ hp + ", kamer= " + huidigeKamer + "}";
    }
}

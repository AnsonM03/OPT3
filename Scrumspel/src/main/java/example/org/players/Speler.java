package example.org.players;

import example.org.Templates.Inventory;
import example.org.Templates.Joker;
import example.org.Templates.SQLSavable;
import example.org.items.HintJoker;
import example.org.items.KeyJoker;
import example.org.utils.SpelerInventory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Speler implements SQLSavable {
    private String naam;
    private int huidigeKamer;
    private int hp;
    private SpelerInventory inventory = new SpelerInventory();

    public Speler(String naam, int hp) {
        this.naam = naam;
        this.hp = hp;
        this.huidigeKamer = 1;
    }


    public void toonStatus() {
        System.out.println("Speler: " + naam);
        System.out.println("Huidige kamer: " + huidigeKamer);
        System.out.println("HP: " + hp);
    }

    public SpelerInventory getInventory() {
        return this.inventory;
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

    public Joker kiesJoker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welke joker wil je gebruiken? (hint / key): ");
        String keuze = scanner.nextLine().toLowerCase();

        switch (keuze) {
            case "hint":
                return new HintJoker();
            case "key":
                return new KeyJoker();
            default:
                System.out.println("Ongeldige keuze, standaard HintJoker wordt gebruikt.");
                return new HintJoker(); // Fallback
        }
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

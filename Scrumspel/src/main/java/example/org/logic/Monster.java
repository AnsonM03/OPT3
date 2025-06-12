package example.org.logic;

import example.org.Templates.HintProvider;
import example.org.Templates.Inventory;
import example.org.Templates.Observer;
import example.org.players.Speler;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Monster implements Observer {

    private int schade;
    private Speler speler;
    private int monsterHealth = 300;
    private HintManager hintManager;

    public Monster(int schade, Speler speler, HintManager hintManager){
        this.schade = schade;
        this.speler = speler;
        this.hintManager = hintManager;
    }

    @Override
    public void update(boolean antwoordCorrect) {
        // Interactieve versie
        if (!antwoordCorrect) {
            System.out.println("👹 Het monster valt aan en doet " + schade + " schade!");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Wil je een item gebruiken (ja/nee)? ");
            String itemAntwoord = scanner.nextLine().trim().toLowerCase();

            if (itemAntwoord.equals("ja")) {
                System.out.print("Welke item wil je gebruiken? ");
                String itemNaam = scanner.nextLine().trim();
                gebruikItem(itemNaam);
            } else {
                schadeToebrengenAanSpeler();
            }

            System.out.print("Wil je een hint? (ja/nee)? ");
            String hintAntwoord = scanner.nextLine().trim().toLowerCase();

            if (hintAntwoord.equals("ja")) {
                geefHint();
            } else {
                System.out.println("Oke dat is ook goed!");
            }

        } else {
            System.out.println("✅ Het monster verdwijnt...");
        }
    }

    // Test Update
    public void update(boolean antwoordCorrect, String confirmatieItem, String itemNaam, String confirmatieHint) {
        if (!antwoordCorrect) {
            System.out.println("👹 Het monster valt aan en doet " + schade + " schade!");

            if (confirmatieItem.equalsIgnoreCase("ja")) {
                gebruikItem(itemNaam);
            } else {
                schadeToebrengenAanSpeler();
            }

            if (confirmatieHint.equalsIgnoreCase("ja")) {
                geefHint();
            } else {
                System.out.println("Oke dat is ook goed!");
            }

        } else {
            System.out.println("✅ Het monster verdwijnt...");
        }
    }

    private void gebruikItem(String itemNaam) {
        Inventory inventory = speler.getInventory();
        boolean gebruikt = inventory.gebruikItem(itemNaam, this);
        if (!gebruikt) {
            schadeToebrengenAanSpeler();
        }
    }

    private void geefHint() {
        String[] opties = {"funny", "help"};
        int randomIndex = ThreadLocalRandom.current().nextInt(opties.length);
        String gekozenHintType = opties[randomIndex];

        String hint = hintManager.geefHint(gekozenHintType);
        System.out.println(hint);
    }



    private void schadeToebrengenAanSpeler() {
        int hp = speler.getHp();
        int nieuweHp = hp - schade;
        speler.setHp(Math.max(nieuweHp, 0));
        System.out.println("💔 Je HP is nu: " + speler.getHp());
    }

    public void neemSchade(int damage){
        monsterHealth -= damage;
        if (monsterHealth < 0) {
            monsterHealth = 0;
        }
        System.out.println("[Monster] Het monster ontvant " + damage + " schade. Resterende hp: " + monsterHealth);

        if (monsterHealth == 0) {
            System.out.println("[Monster] Het monster is verslagen!");
        }
    }
}

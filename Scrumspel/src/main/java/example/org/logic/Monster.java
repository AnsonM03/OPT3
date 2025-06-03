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

    public Monster(int schade, Speler speler){
        this.schade = schade;
        this.speler = speler;
    }

    @Override
    public void update(boolean antwoordCorrect) {
        HintFactory hintFactory = new HintFactory();

        if (!antwoordCorrect) {
            HintProvider funnyHint = hintFactory.getHint("funny");
            System.out.println("👹 Het monster valt aan en doet " + schade + " schade!");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Wil je een item gebruiken (ja/nee)");
            String antwoord = scanner.nextLine().trim().toLowerCase();

            if (antwoord.equals("ja")) {
                System.out.println("Welke item wil je gebruiken?");
                String itemNaam = scanner.nextLine().trim();

                Inventory inventory = speler.getInventory();
                boolean gebruikt = inventory.gebruikItem(itemNaam, this);

                if (!gebruikt) {
                    schadeToebrengenAanSpeler();
                }

            } else {
                schadeToebrengenAanSpeler();
            }

            System.out.println("Wil je een hint? (ja/nee)");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("ja")) {
                String[] opties = {"funny", "help"};
                int randomIndex = ThreadLocalRandom.current().nextInt(opties.length);
                String gekozenHintType = opties[randomIndex];

                HintProvider hint = hintFactory.getHint(gekozenHintType);
                hint.geefHint();

            } else {
                System.out.println("Oke dat is ook goed!");
            }

        } else {
            System.out.println("✅ Het monster verdwijnt...");
        }
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

package example.org.logic;

import example.org.Templates.HintProvider;
import example.org.Templates.Observer;
import example.org.players.Speler;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Monster implements Observer {

    private int schade;
    private Speler speler;

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
            int hp = speler.getHp();
            int hpNaSchade = hp -= schade;
            speler.setHp(hpNaSchade);
            if(hp < 0){
                hp = 0;
            }
            System.out.println("Wil je een hint? (ja/nee)");
            Scanner scanner = new Scanner(System.in);
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

    public int getSchade(){
        return schade;
    }
}

package example.org.utils;

import example.org.Templates.RewardGiver;
import java.util.Scanner;
import example.org.players.Speler;
import example.org.utils.Zwaard;
import example.org.players.Monster;

public class Beloning implements RewardGiver {

    private final Speler speler;
    private final Monster monster;

    public Beloning (Speler speler, Monster monster) {
        this.speler = speler;
        this.monster = monster;
    }

    @Override
    public void grantReward() {
        System.out.println("[Beloning] Je hebt de vraag correct beantwoord!");
        System.out.println("[Beloning] Je hebt het zwaard 'ScrumZwaard' gevonden. Deze kan worden gebruikt om het monster aan te vallen.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Wil je het zwaard nu gebruiken om het monster aan te vallen? (ja/nee): ");
        String keuze = scanner.nextLine();

        if (keuze.equalsIgnoreCase("ja")) {
            speler.getInventory().gebruikItem("ScrumZwaard", monster);
        } else {
            speler.getInventory().voegItemtoe("ScrumZwaard");
            System.out.println("[Speler] Je bewaart het zwaard voor later gebruik.");
        }
}

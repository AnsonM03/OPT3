package example.org.utils;

import example.org.Templates.RewardGiver;
import java.util.Scanner;

public class Beloning implements RewardGiver {

    @Override
    public void grantReward() {
        System.out.println("[Beloning] Je hebt de vraag correct beantwoord!");
        System.out.println("[Beloning] Je hebt het zwaard 'ScrumZwaard' gevonden. Deze kan worden gebruikt om het monster aan te vallen.");

        Zwaard scrumZwaard = new Zwaard("ScrumZwaard");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wil je het zwaard nu gebruiken om het monster aan te vallen? (ja/nee): ");
        String keuze = scanner.nextLine();
        if (keuze.equalsIgnoreCase("ja")) {
            scrumZwaard.attack();
        } else {
            System.out.println("[Speler] Je bewaart het zwaard voor later gebruik.");
        }
    }
}
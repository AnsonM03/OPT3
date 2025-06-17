package example.org.utils;

import example.org.Templates.RewardGiver;
import example.org.wapens.Zwaard;

import java.util.Scanner;

public class ZwaardBeloning implements RewardGiver {
    private SpelerInventory inventory;

    public ZwaardBeloning(SpelerInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void grantReward() {
        System.out.println("[Systeem] Je hebt de vraag correct beantwoord!");
        System.out.println("[Beloning] Je hebt een ScrumZwaard gevonden. Deze kan worden gebruikt om het monster aan te vallen.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Wil je het zwaard nu gebruiken om het monster aan te vallen? (ja/nee): ");
        String keuze = scanner.nextLine();

        if (keuze.equalsIgnoreCase("ja")) {
            System.out.println("[Actie] Je gebruikt het ScrumZwaard tegen het monster!");
            // eventueel: monster.neemSchade(30);
        } else {
            System.out.println("[Speler] Je bewaart het zwaard voor later gebruik.");
            Zwaard scrumZwaard = new Zwaard("ScrumZwaard");
            inventory.voegItemtoe(scrumZwaard);
        }
    }
}
package example.org.logic;

import example.org.Templates.Joker;
import example.org.Templates.Kamer;
import example.org.kamers.*;
import example.org.players.Speler;
import example.org.utils.SQLLoader;
import example.org.utils.SQLSaver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScrumSpel {
    public Map<Integer, Kamer> kamers = new HashMap<>();
    private Speler speler;
    private Monster monster;
    private RoomChanger roomChanger;

    public ScrumSpel() {
        initializeKamers();
    }

    private void initializeKamers() {
        kamers.put(1, SprintPlanningKamer.maakKamer());
        kamers.put(2, DailyScrumKamer.maakKamer());
        kamers.put(3, ScrumBoardKamer.maakKamer());
        kamers.put(4, SprintReviewKamer.maakKamer());
        kamers.put(5, SprintRetrospectiveKamer.maakKamer());
        kamers.put(6, FinaleTIAKamer.maakKamer());
    }

    public void startSpel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer je naam in: ");
        String naam = scanner.nextLine();

        speler = new Speler(naam, 100);
        speler.setHuidigeKamer(1);
        SQLLoader.loadFromDatabase("speler");

        roomChanger = new RoomChanger(speler, kamers);

        // Monster is now an observer, needs speler reference
        monster = new Monster(40, speler);

        // Register observers for all rooms
        for (Kamer kamer : kamers.values()) {
            // Add both door and monster as observers
            kamer.addObserver(kamer.getDeur(), monster);
        }


        System.out.println("\nWelkom bij het ScrumSpel, " + naam + "!");
        System.out.println("Je begint in kamer " + speler.getHuidigeKamer() + " met " + speler.getHp() + " HP.");

        hoofdSpelLoop();
        scanner.close();
    }
    private void hoofdSpelLoop() {
        Scanner scanner = new Scanner(System.in);
        boolean spelActief = true;

        while (spelActief) {
            if (speler.getHp() <= 0) {
                handleGameOver();
                continue;
            }

            roomChanger.toonHuidigeKamer();

            System.out.print("\n> ");
            String input = scanner.nextLine().toLowerCase().trim();

            switch (input) {
                case "status":
                    speler.toonStatus();
                    break;
                case "stop":
                    System.out.println("Spel opslaan en afsluiten...");
                    SQLSaver.saveToDatabase(speler);
                    spelActief = false;
                    break;

                case "joker":
                    Kamer kamer = kamers.get(speler.getHuidigeKamer());
                    Joker gekozenJoker = speler.kiesJoker();
                    kamer.accepteer(gekozenJoker);
                    break;

                case "beantwoord":
                    Kamer huidigeKamer = kamers.get(speler.getHuidigeKamer());
                    boolean correct = huidigeKamer.handlePlayerAnswer();
                    if (correct) {
                        SQLSaver.saveToDatabase(speler);
                    }
                    break;
                default:
                    if (input.startsWith("ga naar kamer ")) {
                        roomChanger.verwerkKamerVerandering(input);
                    } else {
                        System.out.println("Onbekend commando. Beschikbare commando's:");
                        System.out.println("- 'beantwoord' : Probeer de vraag te beantwoorden");
                        System.out.println("- 'ga naar kamer X' : Ga naar kamer X (als unlocked)");
                        System.out.println("- 'status' : Toon je huidige status");
                        System.out.println("- 'stop' : Sla op en stop het spel");
                    }
            }
        }
        scanner.close();
    }
    private void handleGameOver() {
        System.out.println("\nGame over! Je hebt geen HP meer.");
        System.out.println("Je wordt teruggebracht naar kamer 0 met volle HP...");

        speler.setHp(100);
        speler.setHuidigeKamer(0);

        SQLSaver.saveToDatabase(speler);
    }
}
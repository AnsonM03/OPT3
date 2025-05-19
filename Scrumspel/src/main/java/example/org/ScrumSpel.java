package example.org;

import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;
import example.org.players.Monster;
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
        kamers.put(0, StandaardKamer.maakKamer());
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
        SQLLoader.loadFromDatabase("speler");

        roomChanger = new RoomChanger(speler, kamers);

        // Monster is now an observer, needs speler reference
        monster = new Monster(40, speler);

        // Register the monster as observer to all standaardkamers
        for (Kamer kamer : kamers.values()) {
            if (kamer instanceof StandaardKamer) {
                ((StandaardKamer) kamer).addObserver(monster);
            }
        }

        for (Kamer kamer : kamers.values()) {
            if (kamer instanceof StandaardKamer standaardKamer) {
                standaardKamer.addObserver(standaardKamer.getDeur());
            }
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
                case "beantwoord":
                    toonVraagEnControleerAntwoord();
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

        for (Kamer kamer : kamers.values()) {
            if (kamer instanceof StandaardKamer) {
                ((StandaardKamer) kamer).setBeantwoordCorrect(false);
            }
        }

        SQLSaver.saveToDatabase(speler);
    }



    private void toonVraagEnControleerAntwoord() {
        Kamer kamer = kamers.get(speler.getHuidigeKamer());
        if (kamer instanceof StandaardKamer standaardKamer) {
            if (standaardKamer.isBeantwoordCorrect()) {
                System.out.println("Je hebt deze vraag al correct beantwoord!");
                return;
            }

            System.out.print(standaardKamer.getVraag() + "\nJe antwoord: ");
            Scanner scanner = new Scanner(System.in);
            String antwoord = scanner.nextLine();

            boolean correct = standaardKamer.controleerAntwoord(antwoord);

            if (correct) {
                System.out.println("✅ Goed!");
            }

            SQLSaver.saveToDatabase(speler);
        }
    }
}
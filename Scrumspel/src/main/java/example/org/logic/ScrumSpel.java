package example.org.logic;

import example.org.Templates.Item;
import example.org.Templates.Joker;
import example.org.Templates.Kamer;
import example.org.kamers.*;
import example.org.players.Speler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScrumSpel {
    public Map<Integer, Kamer> kamers = new HashMap<>();
    private Speler speler;
    private Monster monster;
    private Deur deur = new Deur(false);
    private RoomChanger roomChanger;
    private Scanner scanner = new Scanner(System.in);
    private boolean spelActief;


    public ScrumSpel() {
    }


    public void startSpel() {
        System.out.println("Voer je naam in: ");
        String naam = scanner.nextLine();

        speler = new Speler(naam, 100);
        kamers = KamerFactory.maakAlleKamers(speler.getInventory());
        speler.setHuidigeKamer(1);

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

    private Kamer getHuidigeKamer() {
        return kamers.get(speler.getHuidigeKamer());
    }

    private void hoofdSpelLoop() {
        spelActief = true;
        while (spelActief) {
            if (speler.getHp() <= 0) {
                handleGameOver();
                continue;
            }

            roomChanger.toonHuidigeKamer();

            System.out.print("\n> ");
            String input = scanner.nextLine().toLowerCase().trim();

            CommandHandler commandHandler = new CommandHandler(speler, getHuidigeKamer(), scanner, () -> spelActief = false);



            if (!commandHandler.verwerkInput(input)) {
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
        System.out.println("Je wordt teruggebracht naar kamer 1 met volle HP...");
        speler.setHp(100);
        deur.setOpen(false);
        speler.setHuidigeKamer(1);
    }
}
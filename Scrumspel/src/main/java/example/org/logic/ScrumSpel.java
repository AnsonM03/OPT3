package example.org.logic;

import example.org.Factory.KamerFactory;
import example.org.Templates.Kamer;
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
    private HintFactory hintFactory;
    private HintManager hintManager;
    private KamerManager kamerManager;
    private Scanner scanner = new Scanner(System.in);
    private boolean spelActief;
    private CommandHandler commandHandler;


    public ScrumSpel() {
    }


    public void startSpel() {
        System.out.println("Voer je naam in: ");
        String naam = scanner.nextLine();

        speler = new Speler(naam, 100);
        kamers = KamerFactory.maakAlleKamers(speler.getInventory());
        speler.setHuidigeKamer(1);

        kamerManager = new KamerManager();
        roomChanger = new RoomChanger(speler, kamers, kamerManager);
        hintFactory = new HintFactory();
        hintManager = new HintManager(hintFactory);
        commandHandler = new CommandHandler(
                speler,
                getHuidigeKamer(),
                scanner,
                ()-> spelActief = false,
                () -> handleGameWin(),
                kamers.size()
        );

        // Monster is now an observer, needs speler reference
        monster = new Monster(40, speler, hintManager, scanner);

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

            Kamer huidigeKamer = getHuidigeKamer();

            if (speler.getHuidigeKamer() == kamers.size() && kamers.get(speler.getHuidigeKamer()).isBeantwoordCorrect()) {
                handleGameWin();
                break;
            }

            huidigeKamer.toonKamerinfo();

            if (!huidigeKamer.isBeantwoordCorrect()) {
                System.out.println("Typ 'beantwoord' om de vraag te beantwoorden.");
            } else {
                int volgendeKamerNummer = speler.getHuidigeKamer() + 1;
                if (kamers.containsKey(volgendeKamerNummer)) {
                    System.out.println("Typ 'ga naar kamer " + volgendeKamerNummer + " ' om naar de volgende kamer te gaan.");
                } else {
                    System.out.println("Je hebt alle kamers voltooid! Gefeliciteerd!");
                }
            }

            System.out.print("\n> ");
            String input = scanner.nextLine().toLowerCase().trim();

            commandHandler.setHuidigeKamer(huidigeKamer);

            if (!commandHandler.verwerkInput(input)) {
                if (input.startsWith("ga naar kamer ")) {
                    roomChanger.verwerkKamerVerandering(input);
                } else {
                    System.out.println("Onbekend commando. Beschikbare commando's:");
                    System.out.println("- 'beantwoord' : Probeer de vraag te beantwoorden");
                    System.out.println("- 'ga naar kamer X' : Ga naar kamer X (als unlocked)");
                    System.out.println("- 'gebruik assistent' : Activeer de assistent (in specifieke kamers)");
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

    private void handleGameWin() {
        System.out.println("\n Gefeliciteerd! Je hebt alle kamers succesvol doorlopen!");
        spelActief = false;
    }
}
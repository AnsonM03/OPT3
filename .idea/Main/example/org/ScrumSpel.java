package example.org;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScrumSpel {
    public Map<Integer, Kamer> kamers = new HashMap<>();
    private Speler speler;
    private Monster monster;

    public ScrumSpel() {
        // opdrachtX must be initialized before kamers
        initializeOpdrachten();
        initializeKamers();
    }
    private void initializeOpdrachten() {
        opdracht1 = new OpenOpdracht("Wat is de hoofdrol van de Product Owner in Scrum?", "Het beheren van de Product Backlog en zorgen dat het team waarde levert.");
        opdracht2 = new OpenOpdracht("Hoe heet de dagelijkse bijeenkomst waar het team synchroniseert?", "De Daily Scrum (of Stand-up).");
        opdracht3 = new OpenOpdracht("Wat is een 'Sprint' in Scrum?", "Een vaste periode (meestal 2-4 weken) waarin een werkbaar product wordt opgeleverd.");
        opdracht4 = new OpenOpdracht("Noem de drie artefacten in Scrum.", "Product Backlog, Sprint Backlog en Increment.");
        opdracht5 = new OpenOpdracht("Wat is het doel van een Retrospective?", "Het team verbetert zijn proces door reflectie.");
    }
    public Opdracht opdracht1, opdracht2, opdracht3, opdracht4, opdracht5;
    private void initializeKamers() {
        kamers.put(0, new StandaardKamer(0, "Je staat in de startkamer", opdracht1));
        kamers.put(1, new StandaardKamer(1, "Kamer 1", opdracht2));
        kamers.put(2, new StandaardKamer(2, "Kamer 2", opdracht3));
        kamers.put(3, new StandaardKamer(3, "Kamer 3", opdracht4));
        kamers.put(4, new StandaardKamer(4, "Kamer 4", opdracht5));
    }
    public void startSpel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer je naam in: ");
        String naam = scanner.nextLine();

        speler = new Speler(naam, 100);
        speler.loadFromDatabase();

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

            toonHuidigeKamer();

            System.out.print("\n> ");
            String input = scanner.nextLine().toLowerCase().trim();

            switch (input) {
                case "status":
                    speler.toonStatus();
                    break;
                case "stop":
                    System.out.println("Spel opslaan en afsluiten...");
                    speler.saveToDatabase();
                    spelActief = false;
                    break;
                case "beantwoord":
                    toonVraagEnControleerAntwoord();
                    break;
                default:
                    if (input.startsWith("ga naar kamer ")) {
                        verwerkKamerVerandering(input);
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

        speler.saveToDatabase();
    }
    private void verwerkKamerVerandering(String input) {
        try {
            int kamernummer = Integer.parseInt(input.replace("ga naar kamer ", ""));
            int huidigeKamerNr = speler.getHuidigeKamer();

            if (kamernummer == huidigeKamerNr) {
                System.out.println("Je bent al in kamer " + kamernummer + "!");
                return;
            }

            if (!kamers.containsKey(kamernummer)) {
                System.out.println("Kamer " + kamernummer + " bestaat niet!");
                return;
            }

            if (kamernummer > huidigeKamerNr + 1) {
                System.out.println("Je kunt alleen naar de volgende kamer gaan (kamer " + (huidigeKamerNr + 1) + ")");
                return;
            }

            // 💡 Check if the deur is open
            if (kamernummer == huidigeKamerNr + 1) {
                Kamer huidigeKamer = kamers.get(huidigeKamerNr);
                if (huidigeKamer instanceof StandaardKamer standaardKamer) {
                    Deur deur = standaardKamer.getDeur();
                    if (!deur.isOpen()) {
                        // 🚫 Deur is gesloten, don't change kamers
                        return;
                    }
                }
            }

            veranderKamer(kamernummer);

        } catch (NumberFormatException e) {
            System.out.println("Ongeldig kamernummer. Gebruik: 'ga naar kamer X' waar X een getal is.");
        }
    }
    private void veranderKamer(int kamerId) {
        if (kamers.containsKey(kamerId)) {
            speler.setHuidigeKamer(kamerId);
            System.out.println("Je bent nu in kamer " + kamerId + ".");
            speler.saveToDatabase();
        } else {
            System.out.println("Kamer " + kamerId + " bestaat niet.");
        }
    }
    private void toonHuidigeKamer() {
        Kamer kamer = kamers.get(speler.getHuidigeKamer());
        System.out.println("\nJe bent nu in kamer " + kamer.getNummer() + ":");
        System.out.println(kamer.getBeschrijving());

        if (kamer instanceof StandaardKamer standaardKamer) {
            if (!standaardKamer.isBeantwoordCorrect()) {
                System.out.println("\nVraag: " + standaardKamer.getVraag());
                System.out.println("Typ 'beantwoord' om te proberen de vraag te beantwoorden.");
            } else {
                standaardKamer.getDeur().displayStatus();
            }
        }
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

            speler.saveToDatabase();
        }
    }
}
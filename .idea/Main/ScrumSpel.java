import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScrumSpel {
    private Map<Integer, Kamer> kamers = new HashMap<>();
    private Speler speler;
    private Monster monster;

    public ScrumSpel() {
        kamers.put(0, new StandaardKamer(0, "Je staat in de startkamer", "Wat is de hoofdrol van de Product Owner in Scrum?", "Het beheren van de Product Backlog en zorgen dat het team waarde levert."));
        kamers.put(1, new StandaardKamer(1, "Kamer 1", "Hoe heet de dagelijkse bijeenkomst waar het team synchroniseert?", "De Daily Scrum (of Stand-up)."));
        kamers.put(2, new StandaardKamer(2, "Kamer 2", "Wat is een 'Sprint' in Scrum?", "Een vaste periode (meestal 2-4 weken) waarin een werkbaar product wordt opgeleverd."));
        kamers.put(3, new StandaardKamer(3, "Kamer 3","Noem de drie artefacten in Scrum.","Product Backlog, Sprint Backlog en Increment."));
        kamers.put(4, new StandaardKamer(4, "Kamer 4", "Wat is het doel van een Retrospective?", "Het team verbetert zijn proces door reflectie."));
        monster = new Monster(20);

    }

    public void startSpel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer je naam in: ");
        String naam = scanner.nextLine();
        speler = new Speler(naam, 100);

        System.out.println("\nWelkom bij het ScrumSpel, " + naam + "!");
        toonHuidigeKamer();

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().toLowerCase();

            if (input.startsWith("ga naar kamer ")) {
                try {
                    int kamernummer = Integer.parseInt(input.replace("ga naar kamer ", ""));
                    veranderKamer(kamernummer);
                } catch (NumberFormatException e) {
                    System.out.println("Ongeldige kamernummer.");
                }
            } else if (input.equals("status")) {
                speler.toonStatus();
            } else if (input.equals("stop")) {
                System.out.println("Spel afgesloten.");
                break;
            } else {
                System.out.println(" onbekend commando. Probeer: 'ga naar kamer X', 'status' of 'stop'.");
            }
        }

        scanner.close();
    }

    private void veranderKamer(int kamerId) {
        if (kamers.containsKey(kamerId)) {
            speler.setHuidigeKamer(kamerId);
            toonHuidigeKamer();
        } else {
            System.out.println("kamer " + kamerId + " bestaat niet.");
        }
    }

    private void toonHuidigeKamer() {
        Kamer kamer = kamers.get(speler.getHuidigeKamer());
        System.out.println("\nJe bent nu in kamer " + kamer.getNummer() + ":");
        System.out.println(kamer.getBeschrijving());

        if (kamer instanceof StandaardKamer) {
            StandaardKamer standaardKamer = (StandaardKamer) kamer;
            System.out.println("Vraag: " + standaardKamer.getVraag());

            Scanner scanner = new Scanner(System.in);
            String antwoord = scanner.nextLine();

            if (standaardKamer.controleerAntwoord(antwoord)) {
                System.out.println("✅ Goed! Je mag verder.");
            } else {
                monster.valAan(speler);  // Monster attacks when wrong answer
                System.out.println("❌ Fout! Het juiste antwoord is: " + standaardKamer.getAntwoord());
                System.out.println("Je huidige HP: " + speler.getHp());  // Show updated HP
            }
        }
    }
}

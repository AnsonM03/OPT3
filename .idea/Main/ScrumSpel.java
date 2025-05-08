import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScrumSpel {
    private Map<Integer, Kamer> kamers = new HashMap<>();
    private Speler speler;

    public ScrumSpel() {
        kamers.put(0, new StandaardKamer(0, "Je staat in de startkamer"));
        kamers.put(1, new StandaardKamer(1, "Kamer 1"));
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
    }
}

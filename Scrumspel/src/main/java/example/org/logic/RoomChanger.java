package example.org.logic;

import example.org.Templates.Kamer;
import example.org.players.Speler;
import example.org.utils.SQLSaver;

import java.util.HashMap;
import java.util.Map;

public class RoomChanger {
    public Map<Integer, Kamer> kamers = new HashMap<>();
    private Speler speler;

    public RoomChanger(Speler speler, Map<Integer, Kamer> kamers){
        this.speler = speler;
        this.kamers = kamers;
    }

    public void veranderKamer(int kamerId) {
        if (kamers.containsKey(kamerId)) {
            speler.setHuidigeKamer(kamerId);
            System.out.println("Je bent nu in kamer " + kamerId + ".");
            SQLSaver.saveToDatabase(speler);
        } else {
            System.out.println("Kamer " + kamerId + " bestaat niet.");
        }
    }

    public void verwerkKamerVerandering(String input) {
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

            // Check if the door is open
            if (kamernummer == huidigeKamerNr + 1) {
                Kamer huidigeKamer = kamers.get(huidigeKamerNr);
                Deur deur = huidigeKamer.getDeur();
                if (deur != null && !deur.isOpen()) {
                    System.out.println("🔒 De deur is nog gesloten!");
                    return;
                }
            }

            veranderKamer(kamernummer);
        } catch (NumberFormatException e) {
            System.out.println("Ongeldig kamernummer. Gebruik: 'ga naar kamer X' waar X een getal is.");
        }
    }

    public void toonHuidigeKamer() {
        Kamer kamer = kamers.get(speler.getHuidigeKamer());
        System.out.println("\nJe bent nu in kamer " + kamer.getNummer() + ":");
        System.out.println(kamer.getBeschrijving());

        if (!kamer.isBeantwoordCorrect()) {
            System.out.println("\nVraag: " + kamer.getVraag());
            System.out.println("Typ 'beantwoord' om te proberen de vraag te beantwoorden.");
        } else {
            Deur deur = kamer.getDeur();
            if (deur != null) {
                deur.update(false);
            }
        }
    }
}

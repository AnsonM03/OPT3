package example.org;

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
    public void veranderKamer(int kamerId) {
        if (kamers.containsKey(kamerId)) {
            speler.setHuidigeKamer(kamerId);
            System.out.println("Je bent nu in kamer " + kamerId + ".");
            SQLSaver.saveToDatabase(speler);
        } else {
            System.out.println("Kamer " + kamerId + " bestaat niet.");
        }
    }

    public void toonHuidigeKamer() {
        Kamer kamer = kamers.get(speler.getHuidigeKamer());
        System.out.println("\nJe bent nu in kamer " + kamer.getNummer() + ":");
        System.out.println(kamer.getBeschrijving());

        if (kamer instanceof StandaardKamer standaardKamer) {
            if (!standaardKamer.isBeantwoordCorrect()) {
                System.out.println("\nVraag: " + standaardKamer.getVraag());
                System.out.println("Typ 'beantwoord' om te proberen de vraag te beantwoorden.");
            } else {
                standaardKamer.getDeur().displayStatus();  // ❗ Laat dit zeker staan zoals je vroeg
            }
        }
    }
}

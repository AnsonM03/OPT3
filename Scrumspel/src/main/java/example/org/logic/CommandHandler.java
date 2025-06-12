package example.org.logic;

import example.org.Templates.Command;
import example.org.Templates.Item;
import example.org.Templates.Joker;
import example.org.Templates.Kamer;
//import example.org.database.SQLSaver;
import example.org.players.Speler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandHandler {
    private final Map<String, Command> commandoMap = new HashMap<>();
    private final Speler speler;
    private final Kamer huidigeKamer;
    private final Scanner scanner;
    private final Runnable spelStopper;

    public CommandHandler(Speler speler, Kamer huidigeKamer, Scanner scanner, Runnable spelStopper) {
        this.speler = speler;
        this.huidigeKamer = huidigeKamer;
        this.scanner = scanner;
        this.spelStopper = spelStopper;
        registreerCommando();
    }

    private void registreerCommando() {
        commandoMap.put("status", () -> speler.toonStatus());
        commandoMap.put("inventory", () -> speler.getInventory().toonInventory());
        commandoMap.put("info", () -> huidigeKamer.toonKamerinfo());

        commandoMap.put("joker", () -> {
            Joker gekozenJoker = speler.kiesJoker();

            if (gekozenJoker instanceof Item item) {
                if (speler.getInventory().heeftItem(item.getNaam())) {
                    huidigeKamer.accepteer(gekozenJoker);
                    speler.getInventory().verwijderItem(item.getNaam());
                } else {
                    System.out.println("Je hebt deze joker niet in je inventory.");
                }
            } else {
                System.out.println("Ongeldige joker.");
            }
        });


        commandoMap.put("beantwoord", () -> {
            boolean correct = huidigeKamer.handlePlayerAnswer();
            if (correct) {
//                SQLSaver.saveToDatabase(speler);
            }
        });

        commandoMap.put("stop", () -> {
            System.out.println("Spel opslaan en afsluiten...");
//            SQLSaver.saveToDatabase(speler);
            spelStopper.run();
        });
    }

    public boolean verwerkInput(String input) {
        Command commando = commandoMap.get(input);
        if (commando != null) {
            commando.execute();
            return true;
        }
        return false;
    }
}

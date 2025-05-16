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
        initializeKamers();
    }
    private void initializeKamers() {
        kamers.put(0, new StandaardKamer(0, "Je staat in de startkamer", opdracht1));
        kamers.put(1, new SprintPlanningKamer(1, "Je staat in Kamer 1 (Sprint Planning Kamer)", sprintOpdracht));
        kamers.put(2, new DailyScrumKamer(2, "Je staat in Kamer 2 (Daily Scrum Kamer). Elke teamgenoot moet een status-update geven. Vergeet je iemand? Dan roept dat het monster 'Vertraging' op.", dailyScrumOpdracht));
        kamers.put(3, new ScrumBoardKamer(3, "Je staat in Kamer 3 voor het Scrum Board. Orden de epics, user stories en taken correct, anders verschijnt het monster 'Chaos'.", scrumBoardOpdracht));
        kamers.put(4, new SprintReviewKamer(4, "Je staat in Kamer 4 (Sprint Review Kamer): Stakeholders geven feedback. Interpreteer hun opmerkingen correct, anders verschijnt het monster 'Miscommunicatie'.", sprintReviewOpdracht));
        kamers.put(5, new SprintRetrospectiveKamer(5, "Sprint Retrospective: Reflecteer op het teamproces. Leer je niet van fouten, dan verschijnt het monster 'Herhaalfouten'.", sprintRetrospectiveOpdracht));
        kamers.put(6, new FinaleTIAKamer(6, "Finale TIA Kamer – Waarom Scrum? Dit is het eindspel! Begrijp je TIA, dan ben je een echte Scrumheld.", finaleTIAOpdracht));
    }

    public Opdracht opdracht1 = new OpenOpdracht("Wat is de hoofdrol van de Product Owner in Scrum?","Het beheren van de Product Backlog en zorgen dat het team waarde levert.");
    public Opdracht sprintOpdracht = new OpenOpdracht("Welke taken neem je op in de sprint planning?",
            "Alleen taken die het team denkt af te krijgen binnen de sprint."
    );
    public Opdracht dailyScrumOpdracht = new OpenOpdracht(
            "Je teamleden zijn: Lisa (developer), Bram (tester), en Noor (Scrum Master). " +
                    "Wie geeft welke update tijdens de Daily Scrum?",
            "Lisa over voortgang development, Bram over testresultaten, Noor over belemmeringen"
    );
    public Opdracht scrumBoardOpdracht = new OpenOpdracht(
            "Hoe richt je een Scrum Board correct in met epics, user stories en taken?",
            "Epics bevatten user stories, user stories bevatten taken. Taken staan in kolommen zoals To Do, In Progress, Done."
    );
    public Opdracht sprintReviewOpdracht = new OpenOpdracht(
            "Stakeholders geven aan dat een opgeleverd onderdeel niet voldoet aan hun verwachtingen. Wat doe je tijdens de Sprint Review?",
            "Je bespreekt de feedback openlijk, past eventueel de Product Backlog aan, en leert voor toekomstige sprints."
    );
    public Opdracht sprintRetrospectiveOpdracht = new OpenOpdracht(
            "Tijdens de sprint waren er veel onderbrekingen door onverwachte verzoeken van buitenaf. Wat kan het team hiervan leren?",
            "Het team moet de sprint beter afbakenen en storingen beperken door duidelijke afspraken met stakeholders te maken."
    );
    public Opdracht finaleTIAOpdracht = new OpenOpdracht(
            "Wat betekent TIA en waarom is Scrum hier een goede aanpak voor?",
            "TIA betekent Things In Action. Scrum is geschikt omdat het snel feedback oplevert, wendbaar is en teamwork bevordert om iteratief tot resultaat te komen."
    );
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
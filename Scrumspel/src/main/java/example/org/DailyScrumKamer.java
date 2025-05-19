package example.org;

import example.org.Templates.Opdracht;

public class DailyScrumKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;

    public DailyScrumKamer(int nummer, String beschrijving, Opdracht opdracht) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
    }

    @Override
    public String getBeschrijving() {
        return beschrijving;
    }

    @Override
    public int getNummer() {
        return nummer;
    }

    @Override
    public Opdracht getOpdracht() {
        return opdracht;
    }

    public static DailyScrumKamer maakKamer() {
        return new DailyScrumKamer(
                2,
                "Je staat in Kamer 2 (Daily Scrum Kamer). Elke teamgenoot moet een status-update geven. Vergeet je iemand? Dan roept dat het monster 'Vertraging' op.",
                new OpenOpdracht(
                        "Je teamleden zijn: Lisa (developer), Bram (tester), en Noor (Scrum Master). " +
                                "Wie geeft welke update tijdens de Daily Scrum?",
                        "Lisa over voortgang development, Bram over testresultaten, Noor over belemmeringen"
                )
        );
    }
}

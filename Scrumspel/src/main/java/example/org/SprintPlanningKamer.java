package example.org;

import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;

public class SprintPlanningKamer extends Kamer {

    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;

    public SprintPlanningKamer(int nummer, String beschrijving, Opdracht opdracht) {
        super(nummer, beschrijving, opdracht);
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

    public static SprintPlanningKamer maakKamer() {
        return new SprintPlanningKamer(
                1,
                "Je staat in Kamer 1 (Sprint Planning Kamer)",
                new OpenOpdracht(
                        "Welke taken neem je op in de sprint planning?",
                        "Alleen taken die het team denkt af te krijgen binnen de sprint."
                )
        );
    }
}
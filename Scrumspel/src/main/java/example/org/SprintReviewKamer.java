package example.org;

import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;

public class SprintReviewKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;

    public SprintReviewKamer(int nummer, String beschrijving, Opdracht opdracht) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
    }

    @Override
    public int getNummer() {
        return nummer;
    }

    @Override
    public String getBeschrijving() {
        return beschrijving;
    }

    @Override
    public Opdracht getOpdracht() {
        return opdracht;
    }

    public static SprintReviewKamer maakKamer() {
        return new SprintReviewKamer(
                4,
                "Je staat in Kamer 4 (Sprint Review Kamer): Stakeholders geven feedback. Interpreteer hun opmerkingen correct, anders verschijnt het monster 'Miscommunicatie'.",
                new OpenOpdracht(
                        "Stakeholders geven aan dat een opgeleverd onderdeel niet voldoet aan hun verwachtingen. Wat doe je tijdens de Sprint Review?",
                        "Je bespreekt de feedback openlijk, past eventueel de Product Backlog aan, en leert voor toekomstige sprints."
                )
        );
    }
}

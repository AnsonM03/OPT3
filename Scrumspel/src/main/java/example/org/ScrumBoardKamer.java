package example.org;

import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;

public class ScrumBoardKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;

    public ScrumBoardKamer(int nummer, String beschrijving, Opdracht opdracht) {
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

    public static ScrumBoardKamer maakKamer() {
        return new ScrumBoardKamer(
                3,
                "Je staat in Kamer 3 voor het Scrum Board. Orden de epics, user stories en taken correct, anders verschijnt het monster 'Chaos'.",
                new OpenOpdracht(
                        "Hoe richt je een Scrum Board correct in met epics, user stories en taken?",
                        "Epics bevatten user stories, user stories bevatten taken. Taken staan in kolommen zoals To Do, In Progress, Done."
                )
        );
    }
}

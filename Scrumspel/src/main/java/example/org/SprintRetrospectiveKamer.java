package example.org;

import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;

public class SprintRetrospectiveKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;

    public SprintRetrospectiveKamer(int nummer, String beschrijving, Opdracht opdracht) {
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

    public static SprintRetrospectiveKamer maakKamer() {
        return new SprintRetrospectiveKamer(
                5,
                "Sprint Retrospective: Reflecteer op het teamproces. Leer je niet van fouten, dan verschijnt het monster 'Herhaalfouten'.",
                new OpenOpdracht(
                        "Tijdens de sprint waren er veel onderbrekingen door onverwachte verzoeken van buitenaf. Wat kan het team hiervan leren?",
                        "Het team moet de sprint beter afbakenen en storingen beperken door duidelijke afspraken met stakeholders te maken."
                )
        );
    }
}

package example.org;

import example.org.Templates.Opdracht;

public class FinaleTIAKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;

    public FinaleTIAKamer(int nummer, String beschrijving, Opdracht opdracht) {
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
}

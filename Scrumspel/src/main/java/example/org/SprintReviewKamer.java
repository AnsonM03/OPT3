package example.org;

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
}

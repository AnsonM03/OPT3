package example.org;

public class SprintPlanningKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;

    public SprintPlanningKamer(int nummer, String beschrijving, Opdracht opdracht) {
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

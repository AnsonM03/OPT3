public class StandaardKamer implements Kamer {
    private int nummer;
    private String beschrijving;

    public StandaardKamer(int nummer, String beschrijving) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
    }

    @Override
    public int getNummer() {
        return nummer;
    }

    @Override
    public String getBeschrijving() {
        return beschrijving;
    }
}

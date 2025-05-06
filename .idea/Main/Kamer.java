public class Kamer {
    private int nummer;
    private String beschrijving;

    public Kamer(int nummer, String beschrijving) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
    }

    public int getNummer() {
        return nummer;
    }

    public String getBeschrijving() {
        return beschrijving;
    }
}

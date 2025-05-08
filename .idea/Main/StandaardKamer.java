public class StandaardKamer implements Kamer {
    private int nummer;
    private String beschrijving;

    private String vraag;
    private String antwoord;

    public StandaardKamer(int nummer, String beschrijving, String vraag, String antwoord) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.vraag = vraag;
        this.antwoord = antwoord;
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
    public String getVraag() {
        return vraag;
    }
    @Override
    public String getAntwoord() {
        return antwoord;
    }

    public boolean controleerAntwoord(String gebruikersAntwoord) {
        return gebruikersAntwoord.equalsIgnoreCase(antwoord);
    }
}

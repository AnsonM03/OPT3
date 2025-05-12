package example.org;

public class StandaardKamer implements Kamer {
    private int nummer;
    private String beschrijving;
    private String vraag;
    private String antwoord;
    private boolean beantwoordCorrect;

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

    public boolean isBeantwoordCorrect() {
        return beantwoordCorrect;
    }

    public void setBeantwoordCorrect(boolean beantwoordCorrect) {
        this.beantwoordCorrect = beantwoordCorrect;
    }

    public boolean controleerAntwoord(String gebruikersAntwoord) {
        String normalizedGebruikersAntwoord = normalizeAntwoord(gebruikersAntwoord);
        String normalizedCorrectAntwoord = normalizeAntwoord(this.antwoord);
        boolean correct = normalizedGebruikersAntwoord.equals(normalizedCorrectAntwoord);
        if (correct) {
            this.beantwoordCorrect = true;
        }
        return correct;
    }

    private String normalizeAntwoord(String antwoord){
        return antwoord.toLowerCase().replaceAll("[.,!?]", "").trim();
    }
}

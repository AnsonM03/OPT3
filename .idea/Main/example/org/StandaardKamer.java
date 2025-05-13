package example.org;

public class StandaardKamer implements Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;

//    private String vraag;
//    private String antwoord;

    public StandaardKamer(int nummer, String beschrijving, Opdracht opdracht) {
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

//    @Override
//    public String getVraag() {
//        return vraag;
//    }
//    @Override
//    public String getAntwoord() {
//        return antwoord;
//    }

    @Override
    public Opdracht getOpdracht() {
        return opdracht;
    }

//    public boolean controleerAntwoord(String gebruikersAntwoord) {
//        String normalizedGebruikersAntwoord = normalizeAntwoord(gebruikersAntwoord);
//        String normalizedCorrectAntwoord = normalizeAntwoord(this.antwoord);
//        return normalizedGebruikersAntwoord.equals(normalizedCorrectAntwoord);
//    }

    private String normalizeAntwoord(String antwoord){
        return antwoord.toLowerCase().replaceAll("[.,!?]", "").trim();
    }
}

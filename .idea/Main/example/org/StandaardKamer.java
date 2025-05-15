package example.org;

public class StandaardKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private boolean beantwoordCorrect = false;

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

    @Override
    public Opdracht getOpdracht() {
        return opdracht;
    }

    public String getVraag() {
        return opdracht.getVraag();
    }

    public boolean controleerAntwoord(String antwoord) {
        boolean correct = opdracht.controleerAntwoord(antwoord);
        if (correct) {
            beantwoordCorrect = true;
        }
        return correct;
    }

    public boolean isBeantwoordCorrect() {
        return beantwoordCorrect;
    }

    public void setBeantwoordCorrect(boolean beantwoordCorrect) {
        this.beantwoordCorrect = beantwoordCorrect;
    }

    public String getAntwoord() {
        if (opdracht instanceof OpenOpdracht) {
            return ((OpenOpdracht) opdracht).getAntwoord();
        } else if (opdracht instanceof MeerkeuzeOpdracht) {
            return ((MeerkeuzeOpdracht) opdracht).getJuistAntwoord();
        } else if (opdracht instanceof PuzzelOpdracht) {
            // Return a string representation of the correct answer
            return "Correcte koppels: " + ((PuzzelOpdracht) opdracht).getJuisteKoppels().toString();
        }
        return "Correct antwoord";
    }

    private String normalizeAntwoord(String antwoord) {
        return antwoord.toLowerCase().replaceAll("[.,!?]", "").trim();
    }
}
package example.org.Templates;

import example.org.Deur;
import example.org.players.Monster;
import example.org.players.Speler;

public abstract class Kamer {
    Speler speler;
    public final void Kamer(){
        getNummer();
        getBeschrijving();
        getOpdracht();
        getDeur();
        isBeantwoordCorrect();
        setBeantwoordCorrect(false);
        getVraag();
        controleerAntwoord("");
        addObserver(new Deur(false), new Monster(40, speler));
        notifyObserver(true);

    }
    public abstract int getNummer();
    public abstract String getBeschrijving();
    public abstract Opdracht getOpdracht();
    public abstract Deur getDeur();
    public abstract boolean isBeantwoordCorrect();
    public abstract void setBeantwoordCorrect(boolean beantwoord);
    public abstract String getVraag();
    public abstract boolean controleerAntwoord(String antwoord);
    public boolean addObserver(Deur deur, Monster monster) {
        addObserver(deur, monster);
        return true;
    }

    public abstract void notifyObserver(boolean antwoordCorrect);
}

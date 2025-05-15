package example.org;

import java.util.ArrayList;
import java.util.List;

public class StandaardKamer implements Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private boolean beantwoordCorrect = false;
    private Deur deur;
    private List<Observer> observers = new ArrayList<>();

    public StandaardKamer(int nummer, String beschrijving, Opdracht opdracht) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = new Deur();
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
    public Deur getDeur() {
        return deur;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(boolean antwoordCorrect) {
        for (Observer observer : observers) {
            observer.update(antwoordCorrect);
        }
    }

    public boolean controleerAntwoord(String antwoord) {
        boolean correct = opdracht.controleerAntwoord(antwoord);
        notifyObservers(correct);
        if (correct) {
            beantwoordCorrect = true;
            deur.open();
        }
        return correct;
    }

    public boolean isBeantwoordCorrect() {
        return beantwoordCorrect;
    }

    public void setBeantwoordCorrect(boolean beantwoordCorrect) {
        this.beantwoordCorrect = beantwoordCorrect;
        if (beantwoordCorrect) {
            deur.open();
        }
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
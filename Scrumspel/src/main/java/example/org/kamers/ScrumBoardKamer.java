package example.org.kamers;

import example.org.Deur;
import example.org.Templates.Kamer;
import example.org.Templates.Observer;
import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;
import example.org.players.Monster;

import java.util.ArrayList;
import java.util.List;

public class ScrumBoardKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private List<Observer> observers = new ArrayList<>();
    private boolean beantwoordCorrect;

    public ScrumBoardKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect = false;
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

    @Override
    public Deur getDeur(){
        return deur;
    }

    @Override
    public boolean isBeantwoordCorrect() {
        return beantwoordCorrect;
    }

    @Override
    public void setBeantwoordCorrect(boolean beantwoord) {
        this.beantwoordCorrect = beantwoord;
        deur.isOpen();
        notifyObserver(true);
    }

    @Override
    public String getVraag() {
        return opdracht.getVraag();
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        return opdracht.controleerAntwoord(antwoord);
    }

    @Override
    public boolean addObserver(Deur deur, Monster monster) {
        return false;
    }

    @Override
    public void notifyObserver(boolean antwoordCorrect) {
        for (Observer observer : observers) {
            observer.update(antwoordCorrect);
        }
    }

    public static ScrumBoardKamer maakKamer() {
        return new ScrumBoardKamer(
                3,
                "Je staat in Kamer 3 voor het Scrum Board. Orden de epics, user stories en taken correct, anders verschijnt het monster 'Chaos'.",
                new OpenOpdracht(
                        "Hoe richt je een Scrum Board correct in met epics, user stories en taken?",
                        "Epics bevatten user stories, user stories bevatten taken. Taken staan in kolommen zoals To Do, In Progress, Done."
                ), new Deur(true)
        );
    }
}

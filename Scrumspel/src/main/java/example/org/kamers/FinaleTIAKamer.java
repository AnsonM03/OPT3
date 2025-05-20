package example.org.kamers;

import example.org.Deur;
import example.org.Templates.Kamer;
import example.org.Templates.Observer;
import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;
import example.org.players.Monster;

import java.util.ArrayList;
import java.util.List;

public class FinaleTIAKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private List<Observer> observers = new ArrayList<>();

    public FinaleTIAKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
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

    @Override
    public Deur getDeur() {
        return deur;
    }

    @Override
    public boolean isBeantwoordCorrect() {
        return false;
    }

    @Override
    public void setBeantwoordCorrect(boolean beantwoord) {

    }

    @Override
    public String getVraag() {
        return "";
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        return false;
    }

    @Override
    public boolean addObserver(Deur deur, Monster monster) {
        return false;
    }

    public void notifyObserver(boolean antwoordCorrect) {
        for (Observer observer : observers) {
            observer.update(antwoordCorrect);
        }
    }

    public static FinaleTIAKamer maakKamer() {
        return new FinaleTIAKamer(
                6,
                "Finale TIA Kamer – Waarom Scrum? Dit is het eindspel! Begrijp je TIA, dan ben je een echte Scrumheld.",
                new OpenOpdracht(
                        "Wat betekent TIA en waarom is Scrum hier een goede aanpak voor?",
                        "TIA betekent Things In Action. Scrum is geschikt omdat het snel feedback oplevert, wendbaar is en teamwork bevordert om iteratief tot resultaat te komen."
                ),  new Deur(true)
        );
    }
}

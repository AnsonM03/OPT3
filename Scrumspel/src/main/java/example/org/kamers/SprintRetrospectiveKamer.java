package example.org.kamers;

import example.org.Deur;
import example.org.Templates.Kamer;
import example.org.Templates.Observer;
import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;
import example.org.players.Monster;

import java.util.ArrayList;
import java.util.List;

public class SprintRetrospectiveKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private boolean beantwoordCorrect;
    private List<Observer> observers = new ArrayList<>();


    public SprintRetrospectiveKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect = false;
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
    public Deur getDeur(){
        return deur;
    }

    @Override
    public boolean isBeantwoordCorrect() {
        return beantwoordCorrect;
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

    @Override
    public void setBeantwoordCorrect(boolean beantwoord) {
        this.beantwoordCorrect = beantwoord;
        notifyObserver(true);
    }

    @Override
    public String getVraag() {
        return opdracht.getVraag();
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        boolean correct = opdracht.controleerAntwoord(antwoord);
        if (correct) {
            setBeantwoordCorrect(true);
            deur.isOpen();
        }
        return correct;
    }

    public static SprintRetrospectiveKamer maakKamer() {
        return new SprintRetrospectiveKamer(
                5,
                "Sprint Retrospective: Reflecteer op het teamproces. Leer je niet van fouten, dan verschijnt het monster 'Herhaalfouten'.",
                new OpenOpdracht(
                        "Tijdens de sprint waren er veel onderbrekingen door onverwachte verzoeken van buitenaf. Wat kan het team hiervan leren?",
                        "Het team moet de sprint beter afbakenen en storingen beperken door duidelijke afspraken met stakeholders te maken."
                ), new Deur(true)
        );
    }
}

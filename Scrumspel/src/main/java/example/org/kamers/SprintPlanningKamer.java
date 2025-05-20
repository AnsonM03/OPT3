package example.org.kamers;

import example.org.Deur;
import example.org.Templates.Kamer;
import example.org.Templates.Observer;
import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;
import example.org.players.Monster;

import java.util.ArrayList;
import java.util.List;

public class SprintPlanningKamer extends Kamer {

    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private boolean beantwoordCorrect;
    private List<Observer> observers = new ArrayList<>();


    public SprintPlanningKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect  = false;
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
    public void setBeantwoordCorrect(boolean beantwoord) {
        this.beantwoordCorrect = beantwoord;
        if (beantwoord) {
            deur.setOpen(true);
        }
    }

    @Override
    public String getVraag() {
        return opdracht.getVraag();
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        return opdracht.controleerAntwoord(antwoord);
    }

    public static SprintPlanningKamer maakKamer() {
        Deur deur = new Deur(false);
        SprintPlanningKamer kamer =  new SprintPlanningKamer(
                1,
                "Je staat in Kamer 1 (Sprint Planning Kamer)",
                new OpenOpdracht(
                        "Welke taken neem je op in de sprint planning?",
                        "Alleen taken die het team denkt af te krijgen binnen de sprint."
                ), deur
        );
        return kamer;
    }

    @Override
    public boolean addObserver(Deur deur, Monster monster) {
        if (deur != null) {
            observers.add(deur);
        }
        if (monster != null) {
            observers.add(monster);
        }
        return true;
    }

    @Override
    public void notifyObserver(boolean antwoordCorrect) {
        for (Observer observer : observers) {
            observer.update(antwoordCorrect);
        }
    }
}
package example.org.kamers;

import example.org.Deur;
import example.org.Templates.Kamer;
import example.org.Templates.Observer;
import example.org.Templates.Opdracht;
import example.org.opdrachten.OpenOpdracht;
import example.org.players.Monster;

import java.util.ArrayList;
import java.util.List;

public class DailyScrumKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private boolean beantwoordCorrect;
    private List<Observer> observers = new ArrayList<>();

    public DailyScrumKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
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
        return opdracht.controleerAntwoord(antwoord);
    }

    @Override
    public boolean addObserver(Deur deur, Monster monster) {
        return false;
    }

    public static DailyScrumKamer maakKamer() {
        return new DailyScrumKamer(
                2,
                "Je staat in Kamer 2 (Daily Scrum Kamer). Elke teamgenoot moet een status-update geven. Vergeet je iemand? Dan roept dat het monster 'Vertraging' op.",
                new OpenOpdracht(
                        "Je teamleden zijn: Lisa (developer), Bram (tester), en Noor (Scrum Master). " +
                                "Wie geeft welke update tijdens de Daily Scrum?",
                        "Lisa over voortgang development, Bram over testresultaten, Noor over belemmeringen"
                ), new Deur(true)
        );
    }
}

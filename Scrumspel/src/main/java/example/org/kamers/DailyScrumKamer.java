package example.org.kamers;

import example.org.Deur;
import example.org.Templates.Kamer;
import example.org.Templates.Observer;
import example.org.Templates.Opdracht;
import example.org.Templates.RewardGiver;
import example.org.opdrachten.MeerkeuzeOpdracht;
import example.org.opdrachten.OpenOpdracht;
import example.org.players.Monster;
import example.org.utils.Beloning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyScrumKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private boolean beantwoordCorrect;
    private List<Observer> observers = new ArrayList<>();
    private RewardGiver beloning;

    public DailyScrumKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect = false;
        this.beloning = new Beloning();
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
        boolean correct = opdracht.controleerAntwoord(antwoord);
        if (correct) {
            setBeantwoordCorrect(true);
            deur.isOpen();
            beloning.grantReward();
        }
        return correct;
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

    public static DailyScrumKamer maakKamer() {
        return new DailyScrumKamer(
                2,
                "Je staat in Kamer 2 (Daily Scrum Kamer). Elke teamgenoot moet een status-update geven. Vergeet je iemand? Dan roept dat het monster 'Vertraging' op.",
                new MeerkeuzeOpdracht(
                        "Tijdens de Daily Scrum staan vier teamleden klaar om hun update te geven. Wie is waarschijnlijk vergeten zijn update te delen, waardoor het monster “Vertraging” opduikt?\n\n" +
                                "Teamleden:\n\n" +
                                "A) Lisa – meldt dat haar taak bijna af is\n" +
                                "B) Ahmed – vraagt hulp bij een blokkade\n" +
                                "C) Sophie – geeft geen update, kijkt op haar telefoon\n" +
                                "D) Tom – deelt zijn voortgang en planning",
                        Arrays.asList(
                                "A) Lisa",
                                "B) Ahmed",
                                "C) Sophie",
                                "D) Tom"
                        ),
                        "C"
                ), new Deur(true)
        );
    }
}

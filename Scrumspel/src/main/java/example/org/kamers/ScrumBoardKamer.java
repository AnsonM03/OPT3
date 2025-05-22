package example.org.kamers;

import example.org.Deur;
import example.org.Templates.Kamer;
import example.org.Templates.Observer;
import example.org.Templates.Opdracht;
import example.org.Templates.RewardGiver;
import example.org.opdrachten.OpenOpdracht;
import example.org.opdrachten.PuzzelOpdracht;
import example.org.players.Monster;
import example.org.utils.Beloning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScrumBoardKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private List<Observer> observers = new ArrayList<>();
    private boolean beantwoordCorrect;
    private RewardGiver beloning;

    public ScrumBoardKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect = false;
        this.beloning = new Beloning();
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
    }

    @Override
    public String getVraag() {
        return opdracht.getVraag();
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        boolean correct = opdracht.controleerAntwoord(antwoord);
        if (correct && !beantwoordCorrect) {
            setBeantwoordCorrect(true);
            beloning.grantReward();
            notifyObserver(true);
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

    public void notifyObserver(boolean antwoordCorrect) {
        for (Observer observer : observers) {
            observer.update(antwoordCorrect);
        }
    }

    public static ScrumBoardKamer maakKamer() {
        return new ScrumBoardKamer(
                3,
                "Je staat in Kamer 3 voor het Scrum Board. Orden de epics, user stories en taken correct, anders verschijnt het monster 'Chaos'.",
                new PuzzelOpdracht(
                        "Koppel de items aan het juiste type:\n" +
                                "- Inlogfunctionaliteit\n" +
                                "- Inloggen met Google\n" +
                                "- Bouw login-knop\n",
                        Map.of(
                                "Inlogfunctionaliteit", "Epic",
                                "Inloggen met Google", "User Story",
                                "Bouw login-knop", "Taak"
                        )
                ), new Deur(true)

        );
    }
}

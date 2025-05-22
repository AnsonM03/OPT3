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

public class FinaleTIAKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private List<Observer> observers = new ArrayList<>();
    private RewardGiver beloning;

    public FinaleTIAKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
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

    public void notifyObserver(boolean antwoordCorrect) {
        for (Observer observer : observers) {
            observer.update(antwoordCorrect);
        }
    }

    public static FinaleTIAKamer maakKamer() {
        return new FinaleTIAKamer(
                6,
                "Finale TIA Kamer – Waarom Scrum? Dit is het eindspel! Begrijp je TIA, dan ben je een echte Scrumheld.",
                new PuzzelOpdracht(
                        "Koppel elke Scrum-rol aan de juiste verantwoordelijkheid:\n" +
                                "- Beheert de product backlog\n" +
                                "- Faciliteert Scrum-evenementen\n" +
                                "- Levert werkende software op",

                        Map.of(
                                "Product Owner", "Beheert de product backlog",
                                "Scrum Master", "Faciliteert Scrum-evenementen",
                                "Ontwikkelteam", "Levert werkende software op"
                        )
                ), new Deur(true)
        );
    }
}

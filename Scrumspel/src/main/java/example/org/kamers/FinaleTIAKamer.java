package example.org.kamers;

import example.org.Templates.*;
import example.org.logic.Deur;
import example.org.opdrachten.OpenOpdracht;
import example.org.opdrachten.PuzzelOpdracht;
import example.org.logic.Monster;
import example.org.utils.Kamerinfo;
import example.org.utils.SpelerInventory;
import example.org.utils.StaffBeloning;

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
    private boolean beantwoordCorrect;
    private Kamerinfo kamerinfo;

    public FinaleTIAKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur, SpelerInventory inventory) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beloning = new StaffBeloning(inventory);
        this.kamerinfo = new Kamerinfo("In deze finale kamer pas je alles toe wat je geleerd hebt. Laat zien dat je het Scrumproces beheerst!");
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
        this.beantwoordCorrect = beantwoord;
    }

    @Override
    public String getVraag() {
        return opdracht.getVraag();
    }

    @Override
    public void toonKamerinfo() {
        kamerinfo.showMessage();
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

    public static FinaleTIAKamer maakKamer(SpelerInventory inventory) {
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
                ), new Deur(true),
                inventory
        );
    }

    @Override
    public void accepteer(JokerVisitor visitor) {
        visitor.visit(this); // ‘this’ is een DailyScrumKamer
    }

}
package example.org.kamers;

import example.org.logic.Deur;
import example.org.Templates.*;
import example.org.items.KeyJoker;
import example.org.opdrachten.OpenOpdracht;
import example.org.logic.Monster;
import example.org.utils.Kamerinfo;
import example.org.utils.PotionBeloning;
import example.org.utils.SpelerInventory;

import java.util.ArrayList;
import java.util.List;

public class SprintReviewKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private boolean beantwoordCorrect;
    private List<Observer> observers = new ArrayList<>();
    private RewardGiver beloning;
    private Kamerinfo kamerinfo;



    public SprintReviewKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur, SpelerInventory inventory) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect = false;
        this.beloning = new PotionBeloning(inventory);
        this.kamerinfo = new Kamerinfo("Tijdens de Sprint Review demonstreert het team het werk dat in de sprint is voltooid aan stakeholders.");
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
    public Deur getDeur() {
        return deur;
    }

    @Override
    public boolean isBeantwoordCorrect() {
        return beantwoordCorrect;
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
    public void toonKamerinfo() {
        kamerinfo.showMessage();
    }

    public void notifyObserver(boolean antwoordCorrect) {
        for (Observer observer : observers) {
            observer.update(antwoordCorrect);
        }
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
    public void accepteer(JokerVisitor visitor) {
        visitor.visit(this); // ‘this’ is een DailyScrumKamer
    }


}


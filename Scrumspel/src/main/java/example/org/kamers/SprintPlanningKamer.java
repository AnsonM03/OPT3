package example.org.kamers;

import example.org.Templates.*;
import example.org.logic.Deur;
import example.org.opdrachten.OpenOpdracht;
import example.org.logic.Monster;
import example.org.players.Speler;
import example.org.utils.*;

import java.util.ArrayList;
import java.util.List;

public class SprintPlanningKamer extends Kamer {

    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private boolean beantwoordCorrect;
    private List<Observer> observers = new ArrayList<>();
    private RewardGiver beloning;
    private RewardGiver hintjokerbeloning;
    private RewardGiver keyjokerbeloning;
    private Kamerinfo kamerinfo;


    public SprintPlanningKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur, SpelerInventory inventory) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect  = false;
        this.beloning = new ZwaardBeloning(inventory);
        this.hintjokerbeloning = new HintJokerBeloning(inventory);
        this.keyjokerbeloning = new KeyJokerBeloning(inventory);
        this.kamerinfo = new Kamerinfo("Tijdens sprint planning selecteert het team werk voor de komende sprint op basis van prioriteit en capaciteit.");
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
            hintjokerbeloning.grantReward();
            keyjokerbeloning.grantReward();
            notifyObserver(true);
        }
        return correct;
    }

    @Override
    public void toonKamerinfo() {
        kamerinfo.showMessage();
    }

    public static SprintPlanningKamer maakKamer(SpelerInventory inventory) {
        return new SprintPlanningKamer(
                1,
                "Je staat in Kamer 1 (Sprint Planning Kamer)",
                new OpenOpdracht(
                        "Welke taken neem je op in de sprint planning?",
                        "Alleen taken die het team denkt af te krijgen binnen de sprint."
                ), new Deur(true),
                inventory
        );
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
    @Override
    public void accepteer(JokerVisitor visitor) {
        visitor.visit(this); // ‘this’ is een DailyScrumKamer
    }

}
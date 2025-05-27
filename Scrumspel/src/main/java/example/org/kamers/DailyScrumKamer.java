package example.org.kamers;

import example.org.Templates.*;
import example.org.logic.Deur;
import example.org.logic.KeyJoker;
import example.org.opdrachten.MeerkeuzeOpdracht;
import example.org.logic.Monster;
import example.org.utils.BoogBeloning;
import example.org.utils.Kamerinfo;
import example.org.utils.SpelerInventory;

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
    private Kamerinfo kamerinfo;

    public DailyScrumKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur, SpelerInventory inventory) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect = false;
        this.beloning = new BoogBeloning(inventory);
        this.kamerinfo = new Kamerinfo("De Daily Scrum is een kort, dagelijks overleg van maximaal 15 minuten. Elk teamlid beantwoordt: wat heb ik gedaan, wat ga ik doen, en loop ik ergens tegenaan?");
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
    public void toonKamerinfo() {
        kamerinfo.showMessage();
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

    public static DailyScrumKamer maakKamer(SpelerInventory inventory) {
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
                ),
                new Deur(true),
                inventory
        );
    }

    @Override
    public void accepteer(Joker joker) {
        if (joker instanceof KeyJoker) {
            System.out.println("🔑 Je ontvangt een extra sleutel in de DailyScrum kamer!");
        } else {
            joker.useIn(this);

        }
    }
}

package example.org.kamers;

import example.org.logic.Deur;
import example.org.Templates.*;
import example.org.opdrachten.MeerkeuzeOpdracht;
import example.org.logic.Monster;
import example.org.utils.Kamerinfo;
import example.org.utils.SchildBeloning;
import example.org.utils.SpelerInventory;

import java.util.ArrayList;
import java.util.List;

public class SprintRetrospectiveKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private boolean beantwoordCorrect;
    private List<Observer> observers = new ArrayList<>();
    private RewardGiver beloning;
    private Kamerinfo kamerinfo;


    public SprintRetrospectiveKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur, SpelerInventory inventory) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect = false;
        this.beloning = new SchildBeloning(inventory);
        this.kamerinfo = new Kamerinfo("In de Sprint Retrospective reflecteert het team op de samenwerking en processen. Wat ging goed? Wat kan beter?");
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
    public void toonKamerinfo() {
        kamerinfo.showMessage();
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

    public static SprintRetrospectiveKamer maakKamer(SpelerInventory inventory) {
        return new SprintRetrospectiveKamer(
                5,
                "Sprint Retrospective: Reflecteer op het teamproces. Leer je niet van fouten, dan verschijnt het monster 'Herhaalfouten'.",
                new MeerkeuzeOpdracht(
                        "Situatie: Tijdens een sprint blijkt dat een belangrijke taak niet af is omdat twee teamleden " +
                                "onafhankelijk van elkaar aan dezelfde feature werkten, zonder afstemming. " +
                                "Wat kan het team hieruit leren?",

                        // Opties
                        List.of(
                                "A) Taken moeten altijd door één persoon worden uitgevoerd",
                                "B) Dagelijkse stand-ups zijn nutteloos en kunnen worden overgeslagen",
                                "C) Betere communicatie tijdens stand-ups had dit kunnen voorkomen",
                                "D) Het team moet stoppen met agile werken"
                        ),

                        // Juiste antwoord (letter only)
                        "C"
                ), new Deur(true),
                inventory
        );
    }
    @Override
    public void accepteer(JokerVisitor visitor) {
        visitor.visit(this); // ‘this’ is een DailyScrumKamer
    }

}
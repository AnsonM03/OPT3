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
import java.util.List;

public class SprintRetrospectiveKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;
    private Deur deur;
    private boolean beantwoordCorrect;
    private List<Observer> observers = new ArrayList<>();
    private RewardGiver beloning;


    public SprintRetrospectiveKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
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

    public static SprintRetrospectiveKamer maakKamer() {
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
                ), new Deur(true)
        );
    }
}

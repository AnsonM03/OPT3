package example.org.kamers;

import example.org.Deur;
import example.org.Templates.Kamer;
import example.org.Templates.Observer;
import example.org.Templates.Opdracht;
import example.org.Templates.RewardGiver;
import example.org.opdrachten.OpenOpdracht;
import example.org.players.Monster;
import example.org.utils.Beloning;
import example.org.utils.Kamerinfo;

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
    private transient Kamerinfo infoBoek = new Kamerinfo("Welkom in de Sprint Review Kamer. Hier leer je omgaan met feedback van stakeholders.");

    public void toonIntro() {
        infoBoek.showMessage();
    }

    public SprintReviewKamer(int nummer, String beschrijving, Opdracht opdracht, Deur deur) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
        this.deur = deur;
        this.beantwoordCorrect = false;
        this.beloning = new Beloning();
        this.infoBoek = new Kamerinfo("Welkom in de Sprint Review Kamer. Hier leer je omgaan met feedback van stakeholders.");
        toonIntro();
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
        return false;
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

    public static SprintReviewKamer maakKamer() {
        return new SprintReviewKamer(
                4,
                "Je staat in Kamer 4 (Sprint Review Kamer): Stakeholders geven feedback. Interpreteer hun opmerkingen correct, anders verschijnt het monster 'Miscommunicatie'.",
                new OpenOpdracht(
                        "Stakeholders geven aan dat een opgeleverd onderdeel niet voldoet aan hun verwachtingen. Wat doe je tijdens de Sprint Review?",
                        "Je bespreekt de feedback openlijk, past eventueel de Product Backlog aan, en leert voor toekomstige sprints."
                ), new Deur(true)
        );
    }
}

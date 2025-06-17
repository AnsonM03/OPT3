package example.org.Templates;

import example.org.logic.Deur;
import example.org.logic.HintFactory;
import example.org.logic.HintManager;
import example.org.logic.Monster;
import example.org.players.Speler;

import java.util.Scanner;

public abstract class Kamer {
    protected Speler speler;

    public final void speelKamer() {
        toonKamerinfo();
        System.out.println("Vraag: " + getVraag());

        if (!isBeantwoordCorrect()) {
            handlePlayerAnswer();
        } else {
            System.out.println("Je hebt deze kamer al voltooid.");
        }
    }

    public abstract void toonKamerinfo();
    public abstract int getNummer();
    public abstract String getBeschrijving();
    public abstract Opdracht getOpdracht();
    public abstract Deur getDeur();
    public abstract boolean isBeantwoordCorrect();
    public abstract void setBeantwoordCorrect(boolean beantwoord);
    public abstract String getVraag();
    public abstract boolean controleerAntwoord(String antwoord);
    public abstract void accepteer(JokerVisitor visitor);


    public boolean handlePlayerAnswer() {
        if (isBeantwoordCorrect()) {
            System.out.println("Je hebt deze vraag al correct beantwoord!");
            return true;
        }

        System.out.print("Je antwoord: ");
        Scanner scanner = new Scanner(System.in);
        String antwoord = scanner.nextLine();

        boolean correct = controleerAntwoord(antwoord);
        if (correct) {
            System.out.println("✅ Goed!");
        } else {
            System.out.println("❌ Fout!");
            notifyObserver(false);
        }
        return correct;
    }

    public abstract boolean addObserver(Deur deur, Monster monster);

    public abstract void notifyObserver(boolean antwoordCorrect);
}

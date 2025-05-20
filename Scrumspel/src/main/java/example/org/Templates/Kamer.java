package example.org.Templates;

import example.org.Deur;
import example.org.players.Monster;
import example.org.players.Speler;

import java.util.Scanner;

public abstract class Kamer {
    Speler speler;
    public final void Kamer(){
        getNummer();
        getBeschrijving();
        getOpdracht();
        getDeur();
        isBeantwoordCorrect();
        setBeantwoordCorrect(false);
        getVraag();
        controleerAntwoord("");
        handlePlayerAnswer();
        addObserver(new Deur(false), new Monster(40, speler));
        notifyObserver(true);

    }
    public abstract int getNummer();
    public abstract String getBeschrijving();
    public abstract Opdracht getOpdracht();
    public abstract Deur getDeur();
    public abstract boolean isBeantwoordCorrect();
    public abstract void setBeantwoordCorrect(boolean beantwoord);
    public abstract String getVraag();
    public abstract boolean controleerAntwoord(String antwoord);

    public boolean handlePlayerAnswer() {
        if (isBeantwoordCorrect()) {
            System.out.println("Je hebt deze vraag al correct beantwoord!");
            return true;
        }

        System.out.println(getVraag());
        System.out.print("Je antwoord: ");
        Scanner scanner = new Scanner(System.in);
        String antwoord = scanner.nextLine();

        boolean correct = controleerAntwoord(antwoord);
        if (correct) {
            System.out.println("✅ Goed!");
            setBeantwoordCorrect(true);
            notifyObserver(true); // Notify observers with success
        } else {
            System.out.println("❌ Fout!");
            notifyObserver(false); // Notify observers with failure
        }
        return correct;
    }
    public boolean addObserver(Deur deur, Monster monster) {
        addObserver(deur, monster);
        return true;
    }

    public abstract void notifyObserver(boolean antwoordCorrect);
}

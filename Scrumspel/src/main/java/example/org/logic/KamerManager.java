package example.org.logic;

public class KamerManager {
    private int aantalBezochteKamers;

    public KamerManager() {
        this.aantalBezochteKamers = 0;
    }

    public void kamerBezocht() {
        aantalBezochteKamers++;
    }

    public int getAantalBezochteKamers() {
        return aantalBezochteKamers;
    }

    public void resetKamers() {
        this.aantalBezochteKamers = 0;
    }
}

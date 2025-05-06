public class Speler {
    private String naam;
    private int huidigeKamer;

    public Speler(String naam) {
        this.naam = naam;
        this.huidigeKamer = 0;
    }

    public void toonStatus() {
        System.out.println("Speler: " + naam);
        System.out.println("Huidige kamer: " + huidigeKamer);
    }

    public int getHuidigeKamer() {
        return huidigeKamer;
    }

    public void setHuidigeKamer(int kamer) {
        this.huidigeKamer = kamer;
    }
}

public class Speler {
    private String naam;
    private int huidigeKamer;
    private int hp;

    public Speler(String naam, int hp) {
        this.naam = naam;
        this.hp = hp;
        this.huidigeKamer = 0;
    }

    public void neemSchade(int schade){
        this.hp -= schade;
        if(this.hp < 0){
            this.hp = 0;
        }
    }

    public void toonStatus() {
        System.out.println("Speler: " + naam);
        System.out.println("Huidige kamer: " + huidigeKamer);
        System.out.println("HP: " + hp);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHuidigeKamer() {
        return huidigeKamer;
    }

    public void setHuidigeKamer(int kamer) {
        this.huidigeKamer = kamer;
    }
}

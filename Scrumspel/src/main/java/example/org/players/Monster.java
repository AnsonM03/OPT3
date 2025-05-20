package example.org.players;

import example.org.Templates.Observer;

public class Monster implements Observer{
    private int schade;
    private Speler speler;

    public Monster(int schade, Speler speler){
        this.schade = schade;
        this.speler = speler;
    }

    @Override
    public void update(boolean antwoordCorrect) {
        if (!antwoordCorrect) {
            System.out.println("❌ Het antwoord is fout!");
            System.out.println("👹 Het monster valt aan en doet " + schade + " schade!");
            int hp = speler.getHp();
            int hpNaSchade = hp -= schade;
            speler.setHp(hpNaSchade);
            if(hp < 0){
                hp = 0;
            }
        } else {
            System.out.println("✅ Het monster verdwijnt...");
        }
    }

    public int getSchade(){
        return schade;
    }
}

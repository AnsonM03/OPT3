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
            System.out.println("👹 Het monster valt aan en doet " + schade + " schade!");
            int hpNaSchade = speler.getHp() - schade;
            if (hpNaSchade < 0) {
                hpNaSchade = 0;
            }
            speler.setHp(hpNaSchade);
            System.out.println("Je hebt nu " + hpNaSchade + " HP over.");
        } else {
            System.out.println("✅ Het monster verdwijnt...");
        }
    }

    public int getSchade(){
        return schade;
    }
}

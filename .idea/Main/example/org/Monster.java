package example.org;

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
            speler.neemSchade(schade);
        } else {
            System.out.println("✅ Het monster verdwijnt...");
        }
    }

    public int getSchade(){
        return schade;
    }
}

package example.org;

public class Monster {
    private int schade;

    public Monster(int schade){
        this.schade = schade;
    }

    public void valAan(Speler speler){
        System.out.println("Je heef fout beantwoord, het monster heeft " + schade + "HP van je afgepakt!");
        int hp = speler.getHp();
        int hpNaSchade = hp -= schade;
        speler.setHp(hpNaSchade);
        if(hp < 0){
            hp = 0;
        }
    }

    public int getSchade(){
        return schade;
    }
}

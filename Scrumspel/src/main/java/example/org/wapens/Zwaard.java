package example.org.wapens;

import example.org.Templates.Item;
import example.org.Templates.Wapen;
import example.org.logic.Monster;

public class Zwaard implements Wapen, Item {
    private String naam;

    public Zwaard(String naam) {
        this.naam = naam;
    }

    @Override
    public void attack(Monster monster) {
        System.out.println("[Zwaard] Je valt het monster aan met " + naam + "!");
        monster.neemSchade(30);
    }

    @Override
    public String getNaam() {
        return naam;
    }
}

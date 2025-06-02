package example.org.wapens;

import example.org.Templates.Item;
import example.org.Templates.Wapen;
import example.org.logic.Monster;

public class ScrumMap implements Wapen, Item {

    @Override
    public String getNaam() {
        return "ScrumMap";
    }

    @Override
    public void attack(Monster monster) {
        System.out.println("");
        monster.neemSchade(0);
    }
}

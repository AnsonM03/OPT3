package example.org.wapens;

import example.org.Templates.Item;
import example.org.Templates.Wapen;
import example.org.logic.Monster;

public class Schild implements Wapen, Item {

    @Override
    public String getNaam() {
        return "ScrumSchild";
    }

    @Override
    public void attack(Monster monster) {
        System.out.println("[Wapen] Je gooit het schild op het monster!");
        monster.neemSchade(40);
    }
}

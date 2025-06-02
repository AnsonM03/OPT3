package example.org.wapens;

import example.org.Templates.Item;
import example.org.Templates.Wapen;
import example.org.logic.Monster;

public class Potion implements Wapen, Item {

    @Override
    public String getNaam() {
        return "HealingPotion";
    }

    @Override
    public void attack(Monster monster) {
        System.out.println("[Wapen] Je gooit een FirePotion op het monster!");
        monster.neemSchade(35);
    }
}

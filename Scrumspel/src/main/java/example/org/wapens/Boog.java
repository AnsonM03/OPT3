package example.org.wapens;

import example.org.Templates.Item;
import example.org.Templates.Wapen;
import example.org.logic.Monster;

public class Boog implements Wapen, Item {

    @Override
    public String getNaam() {
        return "ScrumBoog";
    }

    @Override
    public void attack(Monster monster) {
        System.out.println("[Wapen] Je vuurt een Scrum-pijl af op het monster!");
        monster.neemSchade(40);
    }
}

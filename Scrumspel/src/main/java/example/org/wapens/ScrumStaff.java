package example.org.wapens;

import example.org.Templates.Item;
import example.org.Templates.Wapen;
import example.org.logic.Monster;

public class ScrumStaff implements Wapen, Item {

    @Override
    public String getNaam() {
        return "ScrumStaff";
    }

    @Override
    public void attack(Monster monster) {
        System.out.println("[Wapen] Je vuurt een vuurbal af op het monster!");
        monster.neemSchade(45);
    }
}

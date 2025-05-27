package example.org.wapens;

import example.org.Templates.Item;
import example.org.players.Monster;

public class Boog implements Item {

    @Override
    public String getNaam() {
        return "ScrumBoog";
    }

    public void attack(Monster monster) {
        System.out.println("[Wapen] Je vuurt een Scrum-pijl af op het monster!");
        monster.neemSchade(20);
    }
}

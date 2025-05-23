package example.org.utils;

import example.org.Templates.RewardGiver;
import example.org.Templates.Inventory;

public class BoogBeloning implements RewardGiver {
    private Inventory inventory;

    public BoogBeloning(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void grantReward() {
        if (inventory.heeftItem("ScrumBoog")) {
            System.out.println("[Beloning] Je hebt al een ScrumBoog.");
        } else {
            System.out.println("[Beloning] Je hebt de boog 'ScrumBoog' gevonden! Deze boog kan worden gebruikt om de Monster aan te vallen.");
            inventory.voegItemtoe("ScrumBoog");
        }
    }
}

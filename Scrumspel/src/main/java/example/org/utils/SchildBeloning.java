package example.org.utils;

import example.org.Templates.RewardGiver;
import example.org.Templates.Inventory;

public class SchildBeloning implements RewardGiver {
    private Inventory inventory;

    public SchildBeloning(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void grantReward() {
        if (inventory.heeftItem("ScrumSchild")) {
            System.out.println("[Beloning] Je hebt het ScrumSchild al.");
        } else {
            System.out.println("[Beloning] Je hebt een ScrumSchild gevonden! Neem 20% minder damage");
            inventory.voegItemtoe("ScrumSchild");
        }
    }
}

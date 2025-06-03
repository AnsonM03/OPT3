package example.org.utils;

import example.org.Templates.Inventory;
import example.org.Templates.RewardGiver;
import example.org.items.HintJoker;

public class HintJokerBeloning implements RewardGiver {
    private Inventory inventory;

    public HintJokerBeloning(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void grantReward() {
        if (inventory.heeftItem("HintJoker")) {
            System.out.println("[Beloning] Je hebt al een HintJoker.");
        } else {
            System.out.println("[Beloning] Je hebt een HintJoker gevonden! Deze kan je gebruiken om een hint te krijgen.");
            inventory.voegItemtoe(new HintJoker());
        }
    }
}

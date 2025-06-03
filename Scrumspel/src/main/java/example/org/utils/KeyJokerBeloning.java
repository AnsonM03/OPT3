package example.org.utils;

import example.org.Templates.Inventory;
import example.org.Templates.RewardGiver;
import example.org.items.KeyJoker;

public class KeyJokerBeloning implements RewardGiver {
    private Inventory inventory;

    public KeyJokerBeloning(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void grantReward() {
        if (inventory.heeftItem("KeyJoker")) {
            System.out.println("[Beloning] Je hebt al een KeyJoker.");
        } else {
            System.out.println("[Beloning] Je hebt een KeyJoker gevonden! Deze kan je gebruiken om de antwoord te krijgen.");
            inventory.voegItemtoe(new KeyJoker());
        }
    }
}

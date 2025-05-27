package example.org.utils;

import example.org.Templates.RewardGiver;
import example.org.Templates.Inventory;

public class PotionBeloning implements RewardGiver {
    private Inventory inventory;

    public PotionBeloning(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void grantReward() {
        if (inventory.heeftItem("HealingPotion")) {
            System.out.println("[Beloning] Je hebt al een Healing Potion.");
        } else {
            System.out.println("[Beloning] Je hebt een Healing Potion gevonden! Deze kan je gebruiken om 50HP terug te krijgen.");
            inventory.voegItemtoe("HealingPotion");
        }
    }
}

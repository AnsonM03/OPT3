package example.org.utils;

import example.org.Templates.RewardGiver;
import example.org.Templates.Inventory;
import example.org.wapens.ScrumMap;

import java.util.Map;

public class MapBeloning implements RewardGiver {
    private Inventory inventory;

    public MapBeloning(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void grantReward() {
        if (inventory.heeftItem("ScrumMap")) {
            System.out.println("[Beloning] Je hebt al een Map.");
        } else {
            System.out.println("[Beloning] Je hebt een ScrumMap gevonden! Deze kan je gebruiken om de dungeon te verlaten.");
            inventory.voegItemtoe(new ScrumMap());
        }
    }
}

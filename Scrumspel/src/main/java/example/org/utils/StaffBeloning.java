package example.org.utils;

import example.org.Templates.Inventory;
import example.org.Templates.RewardGiver;
import example.org.wapens.ScrumStaff;

public class StaffBeloning implements RewardGiver {
    private Inventory inventory;

    public StaffBeloning(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void grantReward() {
        if (inventory.heeftItem("ScrumStaff")) {
            System.out.println("[Beloning] Je hebt al een ScrumStaff.");
        } else {
            System.out.println("[Beloning] Je hebt een ScrumStaff gevonden! Deze kan je gebruiken om de Monster aan te vallen.");
            inventory.voegItemtoe(new ScrumStaff());
        }
    }
}

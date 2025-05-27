package example.org.utils;

import example.org.Templates.Inventory;
import example.org.Templates.Item;
import example.org.logic.Monster;
import example.org.wapens.Zwaard;

import java.util.ArrayList;
import java.util.List;

public class SpelerInventory implements Inventory {
    private List<Item> items;

    public SpelerInventory() {
        this.items = new ArrayList<>();
    }

    @Override
    public void voegItemtoe(Item item) {
        if (!items.contains(item)) {
            items.add(item);
            System.out.println("[Inventory] '" + item.getNaam() + "' is toegevoegd aan je inventory.");
        } else {
            System.out.println("[Inventory] Je hebt '" + item.getNaam() + "' is al in je inventory.");
        }
    }

    @Override
    public boolean heeftItem(String naam) {
        for (Item i : items) {
            if (i.getNaam().equalsIgnoreCase(naam)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void toonInventory() {
        if (items.isEmpty()) {
            System.out.println("[Inventory] Je hebt nog geen items in je inventory.");
        } else {
            System.out.println("[Inventory] Je hebt de volgende items in je inventory:");
            for (Item item : items) {
                System.out.println("- " + item.getNaam());
            }
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean gebruikItem(String naam, Monster monster) {
        for (Item item : items) {
            if (item.getNaam().equalsIgnoreCase(naam)) {
                if (item instanceof Zwaard) {
                    ((Zwaard) item).attack(monster);
                    return true;
                }
                System.out.println("[Actie] " + item.getNaam() + " kan niet gebruikt worden.");
                return false;
            }
        }
        System.out.println("[Fout] Je hebt dit item niet.");
        return false;
    }
}

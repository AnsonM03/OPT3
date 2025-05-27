package example.org.utils;

import example.org.Templates.Inventory;
import example.org.logic.Monster;

import java.util.ArrayList;
import java.util.List;

public class SpelerInventory implements Inventory {
    private List<String> items = new ArrayList<>();

    @Override
    public void voegItemtoe(String item) {
        if (!items.contains(item)) {
            items.add(item);
            System.out.println("[Inventory] '" + item + "' is toegevoegd aan je inventory.");
        } else {
            System.out.println("[Inventory] Je hebt '" + item + "' is al in je inventory.");
        }
    }

    @Override
    public boolean heeftItem(String item) {
        return items.contains(item);
    }

    @Override
    public void toonInventory() {
        if (items.isEmpty()) {
            System.out.println("[Inventory] Je hebt nog geen items in je inventory.");
        } else {
            System.out.println("[Inventory] Je hebt de volgende items in je inventory:");
            for (String item : items) {
                System.out.println("- " + item);
            }
        }
    }

    public List<String> getItems() {
        return items;
    }

    public boolean gebruikItem(String item, Monster monster) {
        if (items.contains(item)) {
            if (item.equalsIgnoreCase("ScrumZwaard")) {
                System.out.println("[Actie] Je gebruikt het ScrumZwaard tegen het monster!");
                // monster.neemSchade(30); // bijvoorbeeld 20 schade
                return true;
            }
            System.out.println("[Actie] " + item + " kan niet gebruikt worden.");
        } else {
            System.out.println("[Fout] Je hebt dit item niet.");
        }
        return false;
    }
}

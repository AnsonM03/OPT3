package example.org.Templates;

import example.org.logic.Monster;

public interface Inventory {
    void voegItemtoe(Item item);
    boolean heeftItem(String naam);
    void toonInventory();
    boolean gebruikItem(String naam, Monster monster);
}

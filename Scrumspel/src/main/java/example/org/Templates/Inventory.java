package example.org.Templates;

public interface Inventory {
    void voegItemtoe(Item item);
    boolean heeftItem(String naam);
    void toonInventory();
}

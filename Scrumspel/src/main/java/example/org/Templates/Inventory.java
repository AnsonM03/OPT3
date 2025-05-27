package example.org.Templates;

public interface Inventory {
    void voegItemtoe(String item);
    boolean heeftItem(String item);
    void toonInventory();
}

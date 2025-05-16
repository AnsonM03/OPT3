package example.org;

public class Deur implements Observer {
    private boolean isOpen;

    public Deur() {
        this.isOpen = false;
    }

    @Override
    public void update(boolean antwoordCorrect) {
        if (antwoordCorrect) {
            open();
        }
    }

    public void open() {
        isOpen = true;
    }

    public boolean isOpen() {
        if (!isOpen) {
            System.out.println("🚪 De deur is gesloten. Beantwoord eerst de vraag.");
        }
        return isOpen;
    }


    public void displayStatus() {
        if (isOpen) {
            System.out.println("🟢 De deur is open.");
        } else {
            System.out.println("🔴 De deur is gesloten.");
        }
    }
}

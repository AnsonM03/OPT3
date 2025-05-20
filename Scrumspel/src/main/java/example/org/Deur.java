package example.org;

import example.org.Templates.Observer;

public class Deur implements Observer {
    private boolean open;

    public Deur(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public void update(boolean antwoordCorrect) {
        if (antwoordCorrect) {
            setOpen(true);
        }
    }


    public void displayStatus() {
        if (open) {
            System.out.println("🟢 De deur is open.");
        } else {
            System.out.println("🔴 De deur is gesloten.");
        }
    }
}

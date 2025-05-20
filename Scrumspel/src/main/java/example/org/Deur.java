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

    public void update(boolean antwoordCorrect) {
        if (antwoordCorrect) {
            setOpen(true);
            System.out.println("🔓 De deur gaat open!");
        }
    }

}

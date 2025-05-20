package example.org.utils;

import example.org.Templates.Wapen;

public class Zwaard implements Wapen {
    private String naam;

    public Zwaard(String naam) {
        this.naam = naam;
    }

    @Override
    public void attack() {
        System.out.println("Je valt aan met het zwaard: " + naam);
    }
}

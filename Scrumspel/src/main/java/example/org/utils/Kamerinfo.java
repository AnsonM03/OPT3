package example.org.utils;

import example.org.Templates.MessageProvider;

public class Kamerinfo implements MessageProvider {
    private String info;

    public Kamerinfo(String info) {
        this.info = info;
    }

    @Override
    public void showMessage() {
        System.out.println("Kamerinfo: " + info);
    }
}

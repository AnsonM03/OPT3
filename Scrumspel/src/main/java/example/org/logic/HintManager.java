package example.org.logic;

import example.org.Templates.HintProvider;

import java.util.ArrayList;
import java.util.List;

public class HintManager {
    private HintFactory hintFactory;
    private int aantalHints;

    public HintManager(HintFactory hintFactory) {
        this.hintFactory = hintFactory;
        this.aantalHints = 0;
    }

    public String geefHint(String type) {
        HintProvider provider = hintFactory.getHint(type);
        aantalHints++;  // Tel hint bij
        return provider.geefHint();
    }

    public int getAantalHints() {
        return aantalHints;
    }

    public void resetHints() {
        this.aantalHints = 0;
    }
}

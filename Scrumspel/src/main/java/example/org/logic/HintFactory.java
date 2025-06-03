package example.org.logic;

import example.org.Templates.HintProvider;

public class HintFactory {
    public HintProvider getHint(String hintType) {
        if (hintType == null) {
            return null;
        }
        if (hintType.equalsIgnoreCase("HELP")) {
            return new HelpHintProvider();
        } else if (hintType.equalsIgnoreCase("FUNNY")) {
            return new FunnyHintProvider();
        }

        return null;
    }
}

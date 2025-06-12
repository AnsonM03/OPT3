package example.org.stubs;

import example.org.logic.HelpHintProvider;

public class StubHelpHintProvider extends HelpHintProvider {
    @Override
    public String geefHint(){
        return "Dit is een stubhint!";
    }
}

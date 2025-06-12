package example.org.stubs;

import example.org.logic.FunnyHintProvider;

public class StubFunnyHintProvider extends FunnyHintProvider {
    @Override
    public String geefHint(){
        return "Dit is een funny stubhint!";
    }
}

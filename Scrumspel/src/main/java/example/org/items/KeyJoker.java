package example.org.items;

import example.org.Templates.Item;
import example.org.Templates.Kamer;
import example.org.Templates.SpecialJoker;
import example.org.kamers.DailyScrumKamer;
import example.org.kamers.SprintReviewKamer;

public class KeyJoker implements SpecialJoker, Item {
    private boolean gebruikt = false;

    @Override
    public void useIn(DailyScrumKamer kamer) {
        if (gebruikt) {
            System.out.println("Joker is al gebruikt!");

        } gebruikt = true;
    }

    @Override
    public void useIn(SprintReviewKamer kamer) {
        if (gebruikt) {
            System.out.println("Joker is al gebruikt!");
        } gebruikt = true;
    }

    @Override
    public void useIn(Kamer kamer) {
    }

    @Override
    public String getNaam(){
        return "KeyJoker";
    }

}

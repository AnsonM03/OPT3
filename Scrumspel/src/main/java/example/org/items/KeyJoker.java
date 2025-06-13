package example.org.items;

import example.org.Templates.Item;
import example.org.Templates.Joker;
import example.org.Templates.Kamer;
import example.org.kamers.DailyScrumKamer;
import example.org.kamers.SprintReviewKamer;

public class KeyJoker implements Joker, Item {
    private boolean gebruikt = false;

    @Override
    public void gebruikOp(Kamer kamer) {
        kamer.accepteer(this);
    }

    @Override
    public void visit(DailyScrumKamer kamer) {
        if (gebruikt) {
            System.out.println("Joker is al gebruikt!");
            return;
        }
        kamer.getDeur().isOpen();
        System.out.println("🔓 De deur naar de volgende kamer is geopend door de KeyJoker!");
        gebruikt = true;
    }

    @Override
    public void visit(SprintReviewKamer kamer) {
        if (gebruikt) {
            System.out.println("Joker is al gebruikt!");
            return;
        }
        kamer.getDeur().isOpen();
        System.out.println("🔓 De deur naar de volgende kamer is geopend door de KeyJoker!");
        gebruikt = true;
    }

    @Override
    public void visit(Kamer kamer) {
//        System.out.println("KeyJoker werkt niet in deze kamer.");
    }

    @Override
    public String getNaam() {
        return "KeyJoker";
    }
}

package example.org.items;

import example.org.Templates.Item;
import example.org.Templates.Joker;
import example.org.Templates.Kamer;
import example.org.Templates.JokerVisitor;
import example.org.kamers.DailyScrumKamer;
import example.org.kamers.SprintReviewKamer;

import java.util.HashMap;
import java.util.Map;

public class HintJoker implements Joker, Item {
    private boolean gebruikt = false;

    private static final Map<Integer, String> tips = new HashMap<>();

    static {
        tips.put(1, "Tip voor SprintPlanningKamer: Kijk naar de taken die klaar zijn voor ontwikkeling...");
        tips.put(2, "Tip voor DailyScrumKamer: Denk aan de drie vragen die iedereen tijdens een Daily Scrum moet beantwoorden...");
        tips.put(3, "Tip voor ScrumBoardKamer: Gebruik kolommen zoals 'To Do', 'In Progress' en 'Done'...");
        tips.put(4, "Tip voor SprintReviewKamer: Gebruik de Sprint Review om feedback van stakeholders te bespreken...");
        tips.put(5, "Tip voor SprintRetrospectiveKamer: Reflecteer tijdens de Retrospective op de verstoringen...");
        tips.put(6, "Tip voor FinaleTIAKamer: TIA staat voor 'Things In Action'...");
    }

    @Override
    public void gebruikOp(Kamer kamer) {
        kamer.accepteer(this);
    }

    @Override
    public void visit(Kamer kamer) {
        if (gebruikt) {
            System.out.println("Deze joker is al gebruikt!");
            return;
        }

        String tip = tips.get(kamer.getNummer());
        if (tip != null) {
            System.out.println(tip);
        } else {
            System.out.println("Geen tip beschikbaar voor deze kamer.");
        }

        gebruikt = true;
    }

    // Andere kamers negeren dit
    @Override public void visit(DailyScrumKamer kamer) { visit((Kamer) kamer); }
    @Override public void visit(SprintReviewKamer kamer) { visit((Kamer) kamer); }

    @Override
    public String getNaam() {
        return "HintJoker";
    }
}

package example.org.logic;

import example.org.Templates.Joker;
import example.org.Templates.Kamer;

import java.util.HashMap;
import java.util.Map;

public class HintJoker implements Joker {
    private boolean gebruikt = false;

    private static final Map<Integer, String> tips = new HashMap<>();

    static {
        tips.put(1, "Tip voor SprintPlanningKamer: Kijk naar de taken die klaar zijn voor ontwikkeling — met duidelijke acceptatiecriteria — en die bijdragen aan het sprintdoel.");
        tips.put(2, "Tip voor DailyScrumKamer: Denk aan de drie vragen die iedereen tijdens een Daily Scrum moet beantwoorden: Wat heb je gedaan? Wat ga je doen? Loop je ergens tegenaan?");
        tips.put(3, "Tip voor ScrumBoardKamer: Gebruik kolommen zoals 'To Do', 'In Progress' en 'Done'. Verdeel epics in user stories en die weer in concrete taken.");
        tips.put(4, "Tip voor SprintReviewKamer: Gebruik de Sprint Review om feedback van stakeholders te bespreken en bepaal samen welke aanpassingen nodig zijn voor de volgende sprint.");
        tips.put(5, "Tip voor SprintRetrospectiveKamer: Reflecteer tijdens de Retrospective op de verstoringen en bespreek hoe je beter ‘nee’ kunt zeggen of dergelijke verzoeken kunt kanaliseren via de Product Owner.");
        tips.put(6, "Tip voor FinaleTIAKamer: TIA staat voor 'Things In Action' – studenten leren door te doen. Scrum ondersteunt dit met korte iteraties en veel praktijk.");
    }


    @Override
    public void useIn(Kamer kamer) {
        if (gebruikt) {
            System.out.println("Deze joker is al gebruikt!");
            return;
        }
        int kamerNummer = kamer.getNummer();
        String tip = tips.get(kamerNummer);

        if (tip != null) {
            System.out.println(tip);
        } else {
            System.out.println("Geen tip beschikbaar voor deze kamer.");
        }
        gebruikt = true;
    }
}

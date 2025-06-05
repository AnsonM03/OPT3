package example.org.logic;

import example.org.Templates.Kamer;
import example.org.kamers.*;
import example.org.utils.SpelerInventory;

import java.util.HashMap;
import java.util.Map;

public class KamerFactory {
    public static Map<Integer, Kamer> maakAlleKamers(SpelerInventory inventory) {
        Map<Integer, Kamer> kamers = new HashMap<>();

        kamers.put(1, SprintPlanningKamer.maakKamer(inventory));
        kamers.put(2, DailyScrumKamer.maakKamer(inventory));
        kamers.put(3, ScrumBoardKamer.maakKamer(inventory));
        kamers.put(4, SprintReviewKamer.maakKamer(inventory));
        kamers.put(5, SprintRetrospectiveKamer.maakKamer(inventory));
        kamers.put(6, FinaleTIAKamer.maakKamer(inventory));

        return kamers;
    }
}

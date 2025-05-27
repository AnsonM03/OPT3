package example.org.Templates;

import example.org.kamers.DailyScrumKamer;
import example.org.kamers.SprintReviewKamer;

public interface SpecialJoker extends Joker{
    void useIn(DailyScrumKamer kamer);
    void useIn(SprintReviewKamer kamer);
}

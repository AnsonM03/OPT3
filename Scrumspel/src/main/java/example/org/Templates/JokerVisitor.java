package example.org.Templates;

import example.org.kamers.DailyScrumKamer;
import example.org.kamers.SprintReviewKamer;

public interface JokerVisitor {
    void visit(DailyScrumKamer kamer);
    void visit(SprintReviewKamer kamer);
    void visit(Kamer kamer); // fallback voor standaardgedrag
}



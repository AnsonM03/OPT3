package example.org.assistent;

import example.org.Templates.EducatiefHulpmiddelProvider;
import example.org.Templates.Kamer;

public class ScrumEducatiefHulpmiddel implements EducatiefHulpmiddelProvider {
    @Override
    public void biedEducatiefHulpmiddel(Kamer kamer) {
        System.out.println("Stappen in het Scrum proces:");
        System.out.println("1. Product Backlog verfijnen");
        System.out.println("2. Sprint Planning");
        System.out.println("3. Daily Scrum");
        System.out.println("4. Sprint Review");
        System.out.println("5. Sprint Retrospective");
    }

}

package example.org.logic;

import example.org.Templates.HintProvider;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HelpHintProvider implements HintProvider {
    private List<String> helpHints = Arrays.asList(
            "De Product Owner bepaalt wat er gebouwd wordt en in welke volgorde.",
            "De Daily Scrum is bedoeld om de voortgang en obstakels van het team te bespreken.",
            "Een Sprint duurt meestal 2 tot 4 weken en levert een werkend product op.",
            "De drie artefacten in Scrum zijn: Product Backlog, Sprint Backlog en Increment.",
            "De Retrospective is bedoeld om het teamproces te verbeteren na elke Sprint.",
            "Op het Scrum Board staan taken in kolommen zoals 'To Do', 'In Progress' en 'Done'.",
            "In Scrum zijn er drie rollen: Product Owner, Scrum Master en Development Team.",
            "De Sprint Review gaat over wat er is opgeleverd, de Retrospective over hoe het proces ging.",
            "Artefacten in Scrum zijn hulpmiddelen die inzicht geven in het werk en de voortgang.",
            "Tijdens een Sprint verandert het doel niet: focus en samenwerking zijn belangrijk."
    );

    @Override
    public void geefHint(){
        int randomGetal = ThreadLocalRandom.current().nextInt(0, helpHints.size());
        System.out.println(helpHints.get(randomGetal));
    }

}

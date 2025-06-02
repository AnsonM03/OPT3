package example.org.logic;

import example.org.Templates.HintProvider;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;




public class FunnyHintProvider implements HintProvider {
    private List<String> funnyHints = Arrays.asList(
            "Wist je dat een Scrum Master geen ninja is, ook al klinkt het zo?",
            "De Daily Scrum is niet bedoeld om je lunch te bespreken, tenzij het 'Blocked by Pizza' is.",
            "Epics zijn geen Griekse heldenverhalen, maar grote user stories. Sorry, Homerus.",
            "Stand-ups zijn geen comedy shows… maar voel je vrij om te lachen als iemand 'blocked' zegt.",
            "Scrum is geen gerecht, al klinkt het als een visstoofpot.",
            "De Definition of Done is niet 'Ik ben moe, dus ik stop'.",
            "Als je denkt dat 'velocity' iets met raceauto’s te maken heeft… je bent niet alleen.",
            "De Product Owner beslist wat belangrijk is. Niet jij. Zelfs niet als jij pizza aanbiedt.",
            "Een increment is geen toverspreuk, maar het levert wel magie op als het goed werkt.",
            "Scrumban is geen nieuw hip drankje, ook al zou het lekker klinken.",
            "De Sprint Review is geen sportwedstrijd, ook al telt het resultaat mee.",
            "User stories zijn geen sprookjes. Er zijn wél monsters (bugs).",
            "Je mag tijdens de Retrospective niet zeggen: 'Alles was perfect.' Nice try.",
            "De backlog is geen zwarte lijst van taken die je wilt vermijden. (Maar soms voelt het zo...)",
            "Scrum werkt niet met magie, maar goed samenwerken lijkt er verdacht veel op."
    );

    @Override
    public void geefHint(){
        int randomGetal = ThreadLocalRandom.current().nextInt(0, funnyHints.size());
        System.out.println(funnyHints.get(randomGetal));
    }
}

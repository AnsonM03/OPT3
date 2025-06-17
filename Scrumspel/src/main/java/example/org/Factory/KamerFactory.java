package example.org.Factory;

import example.org.Templates.Kamer;
import example.org.kamers.*;
import example.org.logic.Deur;
import example.org.opdrachten.MeerkeuzeOpdracht;
import example.org.opdrachten.OpenOpdracht;
import example.org.opdrachten.PuzzelOpdracht;
import example.org.utils.SpelerInventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KamerFactory {
    public static DailyScrumKamer maakDailyScrumKamer(SpelerInventory inventory) {
        return new DailyScrumKamer(
                2,
                "Je staat in Kamer 2 (Daily Scrum Kamer). Elke teamgenoot moet een status-update geven.Vergeet je iemand? Dan roept dat het monster 'Vertraging' op.",
                new MeerkeuzeOpdracht(
                        "Tijdens de Daily Scrum staan vier teamleden klaar om hun update te geven. Wie is waarschijnlijk vergeten zijn update te delen, waardoor het monster “Vertraging” opduikt?\n\n" +
                                "Teamleden:\n\n" +
                                "A) Lisa – meldt dat haar taak bijna af is\n" +
                                "B) Ahmed – vraagt hulp bij een blokkade\n" +
                                "C) Sophie – geeft geen update, kijkt op haar telefoon\n" +
                                "D) Tom – deelt zijn voortgang en planning",
                        Arrays.asList(
                                "A) Lisa",
                                "B) Ahmed",
                                "C) Sophie",
                                "D) Tom"
                        ),
                        "C"
                ),
                new Deur(true),
                inventory
        );
    }




    public static SprintPlanningKamer maakSprintPlanningKamer(SpelerInventory inventory) {
        return new SprintPlanningKamer(
        1,
        "Je staat in Kamer 1 (Sprint Planning Kamer)",
        new OpenOpdracht(
                "Welke taken neem je op in de sprint planning?",
                "Alleen taken die het team denkt af te krijgen binnen de sprint."
            ),
            new Deur(true),
            inventory
        );
    }

    public static Kamer maakScrumBoardKamer(SpelerInventory inventory) {
        return new ScrumBoardKamer(
                3,
                "Je staat in Kamer 3 (Scrumboard Kamer): Het team visualiseert hier de voortgang. Als het board niet up-to-date is, raakt het team het overzicht kwijt en verschijnt het monster 'Chaos'.",
                new MeerkeuzeOpdracht(
                        "Waarom is het belangrijk om het Scrumboard regelmatig bij te werken?",
                        List.of(
                                "A) Om het teamgemiddelde te berekenen",
                                "B) Zodat het team visueel overzicht heeft van de voortgang",
                                "C) Om managers tevreden te houden",
                                "D) Omdat het verplicht is volgens Scrum-regels"
                        ),
                        "B"
                ),
                new Deur(true),
                inventory
        );
    }


    public static Kamer maakSprintReviewKamer(SpelerInventory inventory) {
        return new SprintReviewKamer(
                4,
                "Je staat in Kamer 4 (Sprint Review Kamer): Stakeholders geven feedback. Interpreteer hun opmerkingen correct, anders verschijnt het monster 'Miscommunicatie'.",
                new OpenOpdracht(
                        "Stakeholders geven aan dat een opgeleverd onderdeel niet voldoet aan hun verwachtingen. Wat doe je tijdens de Sprint Review?",
                        "Je bespreekt de feedback openlijk, past eventueel de Product Backlog aan, en leert voor toekomstige sprints."
                ),
                new Deur(true),
                inventory
        );
    }


    public static Kamer maakSprintRetrospectiveKamer(SpelerInventory inventory) {
        return new SprintRetrospectiveKamer(
                5,
                "Sprint Retrospective: Reflecteer op het teamproces. Leer je niet van fouten, dan verschijnt het monster 'Herhaalfouten'.",
                new MeerkeuzeOpdracht(
                        "Situatie: Tijdens een sprint blijkt dat een belangrijke taak niet af is omdat twee teamleden " +
                                "onafhankelijk van elkaar aan dezelfde feature werkten, zonder afstemming. " +
                                "Wat kan het team hieruit leren?",
                        List.of(
                                "A) Taken moeten altijd door één persoon worden uitgevoerd",
                                "B) Dagelijkse stand-ups zijn nutteloos en kunnen worden overgeslagen",
                                "C) Betere communicatie tijdens stand-ups had dit kunnen voorkomen",
                                "D) Het team moet stoppen met agile werken"
                        ),
                        "C"
                ),
                new Deur(true),
                inventory
        );
    }


    public static Kamer maakFinaleTIAKamer(SpelerInventory inventory) {
        return new FinaleTIAKamer(
                6,
                "Finale TIA Kamer – Waarom Scrum? Dit is het eindspel! Begrijp je TIA, dan ben je een echte Scrumheld.",
                new PuzzelOpdracht(
                        "Koppel elke Scrum-rol aan de juiste verantwoordelijkheid:\n" +
                                "- Beheert de product backlog\n" +
                                "- Faciliteert Scrum-evenementen\n" +
                                "- Levert werkende software op",
                        Map.of(
                                "Product Owner", "Beheert de product backlog",
                                "Scrum Master", "Faciliteert Scrum-evenementen",
                                "Ontwikkelteam", "Levert werkende software op"
                        )
                ),
                new Deur(true),
                inventory
        );
    }


    public static Map<Integer, Kamer> maakAlleKamers(SpelerInventory inventory) {
        Map<Integer, Kamer> kamers = new HashMap<>();
        kamers.put(1, maakSprintPlanningKamer(inventory));
        kamers.put(2, maakDailyScrumKamer(inventory));
        kamers.put(3, maakScrumBoardKamer(inventory));
        kamers.put(4, maakSprintReviewKamer(inventory));
        kamers.put(5, maakSprintRetrospectiveKamer(inventory));
        kamers.put(6, maakFinaleTIAKamer(inventory));
        return kamers;
    }
}

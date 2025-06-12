package example.org;

import example.org.Templates.MotivatieBerichtProvider;

import java.util.concurrent.ThreadLocalRandom;

public class ScrumMotivatieBericht implements MotivatieBerichtProvider {
    private static final String[] BERICHTEN = {
            "Je denkt als een echte Product Owner!",
            "Goed bezig! Je Scrum-vaardigheden worden steeds beter!",
            "Fantastisch! Je hebt precies de juiste Scrum-mindset!",
            "Ga zo door! Je bent een geboren Scrum Master!",
            "Uitstekend! Je begrijpt de Scrum-waarden perfect!",
            "Top! Je bent op weg naar Scrum-meesterschap!"
    };

    @Override
    public void biedMotivatie() {
        int willekeurigeIndex = ThreadLocalRandom.current().nextInt(BERICHTEN.length);
        System.out.println(BERICHTEN[willekeurigeIndex]);
    }
}

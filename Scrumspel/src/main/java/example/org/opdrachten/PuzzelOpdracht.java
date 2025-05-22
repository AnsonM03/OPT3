package example.org.opdrachten;

import example.org.Templates.Opdracht;

import java.util.Map;

public class PuzzelOpdracht implements Opdracht {
    private String vraag;
    private Map<String, String> juisteKoppels;

    public PuzzelOpdracht(String vraag, Map<String, String> juisteKoppels) {
        this.vraag = vraag;
        this.juisteKoppels = juisteKoppels;
    }

    @Override
    public String getVraag() {
        return vraag + "\n(Geef antwoorden in het formaat: sleutel1=waarde1, sleutel2=waarde2, ...)";
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        String normalizedInput = antwoord.replaceAll("\\s*=\\s*", "=")  // Remove spaces around =
                .replaceAll("\\s*,\\s*", ","); // Remove spaces around ,

        String expected = juisteKoppels.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .reduce((a, b) -> a + "," + b)
                .orElse("");

        return normalizedInput.equalsIgnoreCase(expected);
    }

    public Map<String, String> getJuisteKoppels(){
        return juisteKoppels;
    }
}

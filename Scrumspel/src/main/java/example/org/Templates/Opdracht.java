package example.org.Templates;

public interface Opdracht {
    String getVraag();
    boolean controleerAntwoord(String antwoord);
    String getAntwoord();
}

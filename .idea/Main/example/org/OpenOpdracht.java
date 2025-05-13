package example.org;

public class OpenOpdracht implements Opdracht{
    private String vraag;
    private String antwoord;

    public OpenOpdracht(String vraag, String antwoord){
        this.vraag = vraag;
        this.antwoord = antwoord;
    }

    @Override
    public String getVraag() {
        return this.vraag;
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        return this.antwoord.equalsIgnoreCase(antwoord.trim());
    }

}

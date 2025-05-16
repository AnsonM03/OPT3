package example.org;

import java.util.List;

public class MeerkeuzeOpdracht implements Opdracht{
    private String vraag;
    private List<String> opties;
    private String juistAntwoord;


    public MeerkeuzeOpdracht(String vraag, List<String> opties, String juistAntwoord) {
        this.vraag = vraag;
        this.opties = opties;
        this.juistAntwoord = juistAntwoord;
    }

    @Override
    public String getVraag() {
        return vraag + "\nOpties: " + String.join(", ", opties);
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        return this.juistAntwoord.equalsIgnoreCase(antwoord.trim());
    }

    public String getJuistAntwoord(){
        return juistAntwoord;
    }

}

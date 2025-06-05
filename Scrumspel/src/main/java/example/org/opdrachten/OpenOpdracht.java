package example.org.opdrachten;

import example.org.Templates.Opdracht;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public String getAntwoord(){
        return this.antwoord;
    }

    @Override
    public String toString() {
        return "OpenOpdracht{vraag='" + vraag + "', antwoord='" + antwoord + "'}";
    }


}

package example.org;

public class FinaleTIAKamer extends Kamer {
    private int nummer;
    private String beschrijving;
    private Opdracht opdracht;

    public FinaleTIAKamer(int nummer, String beschrijving, Opdracht opdracht) {
        this.nummer = nummer;
        this.beschrijving = beschrijving;
        this.opdracht = opdracht;
    }

    @Override
    public String getBeschrijving() {
        return beschrijving;
    }

    @Override
    public int getNummer() {
        return nummer;
    }

    @Override
    public Opdracht getOpdracht() {
        return opdracht;
    }

    public static FinaleTIAKamer maakKamer() {
        return new FinaleTIAKamer(
                6,
                "Finale TIA Kamer – Waarom Scrum? Dit is het eindspel! Begrijp je TIA, dan ben je een echte Scrumheld.",
                new OpenOpdracht(
                        "Wat betekent TIA en waarom is Scrum hier een goede aanpak voor?",
                        "TIA betekent Things In Action. Scrum is geschikt omdat het snel feedback oplevert, wendbaar is en teamwork bevordert om iteratief tot resultaat te komen."
                )
        );
    }
}

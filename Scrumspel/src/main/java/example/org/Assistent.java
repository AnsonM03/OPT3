package example.org;

import example.org.Templates.EducatiefHulpmiddelProvider;
import example.org.Templates.HintProvider;
import example.org.Templates.Kamer;
import example.org.Templates.MotivatieBerichtProvider;

public class Assistent {

    private final HintProvider hintProvider;
    private final EducatiefHulpmiddelProvider educatiefHulpmiddel;
    private final MotivatieBerichtProvider motivatieBericht;

    public Assistent(HintProvider hintProvider,
                     EducatiefHulpmiddelProvider educatiefHulpmiddel,
                     MotivatieBerichtProvider motivatieBericht) {
        this.hintProvider = hintProvider;
        this.educatiefHulpmiddel = educatiefHulpmiddel;
        this.motivatieBericht = motivatieBericht;
    }

    public void activeer(Kamer kamer) {
        // Toon hint
        System.out.println("\n💡 Assistent hint:");
        hintProvider.geefHint();

        // Toon educatief hulpmiddel
        System.out.println("\n📚 Educatief hulpmiddel:");
        educatiefHulpmiddel.biedEducatiefHulpmiddel(kamer);

        // Toon motiverend bericht
        System.out.println("\n✨ Motiverend bericht:");
        motivatieBericht.biedMotivatie();
    }
}

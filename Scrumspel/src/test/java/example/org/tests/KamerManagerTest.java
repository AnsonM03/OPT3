package example.org.tests;

import example.org.logic.KamerManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KamerManagerTest {
    @Test
    public void testKamerManagerTeltBezochteKamersCorrect() {
        KamerManager manager = new KamerManager();

        manager.kamerBezocht();
        manager.kamerBezocht();
        manager.kamerBezocht();

        assertEquals(3, manager.getAantalBezochteKamers());
    }

}

package example.org.tests;

import example.org.Templates.HintProvider;
import example.org.logic.HintFactory;
import example.org.logic.HintManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HintManagerTest {
    @Test
    public void testHintManagerTeltHintsCorrect() {
        HintFactory mockFactory = mock(HintFactory.class);
        HintProvider mockHint = mock(HintProvider.class);
        when(mockFactory.getHint(anyString())).thenReturn(mockHint);
        when(mockHint.geefHint()).thenReturn("Testhint");

        HintManager manager = new HintManager(mockFactory);

        manager.geefHint("funny");
        manager.geefHint("help");

        assertEquals(2, manager.getAantalHints());
    }

}

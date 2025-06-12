package example.org.tests;

import example.org.Templates.HintProvider;
import example.org.logic.HintFactory;
import example.org.logic.Monster;
import example.org.players.Speler;
import example.org.utils.SpelerInventory;
import org.junit.jupiter.api.Test;



import static org.mockito.Mockito.*;

public class MockHintProvider {
    @Test
    public void testMonsterGeeftHintAlsAntwoordFoutIs() {
        // Arrange
        HintFactory mockFactory = mock(HintFactory.class);
        HintProvider mockHint = mock(HintProvider.class);
        when(mockFactory.getHint(anyString())).thenReturn(mockHint);
        when(mockHint.geefHint()).thenReturn("Dit is een testhint!");

        Speler mockSpeler = mock(Speler.class);
        SpelerInventory mockInventory = mock(SpelerInventory.class);
        when(mockSpeler.getInventory()).thenReturn(mockInventory);
        when(mockInventory.gebruikItem(anyString(), any())).thenReturn(true);
        when(mockSpeler.getHp()).thenReturn(100);


        Monster monster = new Monster(50, mockSpeler, mockFactory);

        // Act
        monster.update(false, "ja", "potion", "ja");

        // Assert
        verify(mockInventory).gebruikItem("potion", monster);
        verify(mockHint).geefHint();
    }
}

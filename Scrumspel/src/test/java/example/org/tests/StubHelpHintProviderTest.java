package example.org.tests;

import example.org.stubs.StubHelpHintProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubHelpHintProviderTest {

    @Test
    public void testGeefHelpHint(){
        // Arrange
        StubHelpHintProvider stub = new StubHelpHintProvider();

        // Act
        String hint = stub.geefHint();

        // Assert
        assertEquals("Dit is een stubhint!", hint);

    }
}

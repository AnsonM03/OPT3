package example.org.tests;

import example.org.stubs.StubFunnyHintProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubFunnyHintProviderTest {
    @Test
    public void testGeefFunnyHint(){
        // Arrange
        StubFunnyHintProvider stub = new StubFunnyHintProvider();

        // Act
        String hint = stub.geefHint();

        // Assert
        assertEquals("Dit is een funny stubhint!", hint);

    }
}

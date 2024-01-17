import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTestUnit {
    private Card card;

    @Before
    public void setUp() {
        card = new Card(5);
        
    }

    @Test
    public void cardInitializationTest() {
        assertEquals("Card rank should be 5", 5, card.getRank());
        assertNotNull("Card image should not be null", card.getImage());
    }

    @Test
    public void selectCardTest() {
        assertFalse("Initially, card should not be selected", card.isSelected());
        card.selectedCard();
        assertTrue("Card should be selected after selectCard method", card.isSelected());
    }

    @Test
    public void resetCardTest() {
        card.selectedCard();
        card.resetCard();
        assertFalse("Card should not be selected after resetCard method", card.isSelected());
    }
}

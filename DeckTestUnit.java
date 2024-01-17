import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class DeckTestUnit {
    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testDealFourCards() {
        deck.shuffle();
        ArrayList<Card> dealtCards = deck.deal(4);

        assertEquals("Four cards should be dealt", 4, dealtCards.size());

        for (Card card : dealtCards) {
            assertNotNull("Card image should not be null", card.getImage());
        }
    }
}

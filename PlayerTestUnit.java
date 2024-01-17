import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTestUnit {
    private Player player;
    private Card card;

    @Before
    public void setUp() {
        player = new Player("John Doe");
        card = new Card(1); 
    }

    @Test
    public void testAddCard() {
        assertTrue("Initially, player should have no cards", player.getCards().isEmpty());
        player.addCard(card);
        assertFalse("Player should have cards after adding one", player.getCards().isEmpty());
        assertTrue("Player should have the added card", player.getCards().contains(card));
    }

    @Test
    public void testIncrementScore() {
        assertEquals("Initial score should be 0", 0, player.getScore());
        player.incrementScore();
        assertEquals("Score should be incremented", 1, player.getScore());
    }

    @Test
    public void testResetPlayer() {
        player.addCard(card);
        player.incrementScore();
        player.resetPlayer();
        assertEquals("Score should be reset to 0", 0, player.getScore());
        assertTrue("Cards should be cleared after reset", player.getCards().isEmpty());
    }

    @Test
    public void testHasCard() {
        player.addCard(card);
        assertTrue("Player should have the card", player.hasCard(card));
        Card anotherCard = new Card(2);
        assertFalse("Player should not have a card not added", player.hasCard(anotherCard));
    }

}

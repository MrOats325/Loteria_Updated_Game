import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (int i = 1; i <= 54; i++) {
            cards.add(new Card(i));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public ArrayList<Card> deal(int numberOfCards) {
        ArrayList<Card> dealtCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (!cards.isEmpty()) {
                dealtCards.add(cards.remove(0));
            }
        }
        return dealtCards;
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }
}

import java.util.ArrayList;
import java.util.Map;

/**
 * This is the AIPlayer Class that will similuate the moves of the simple AI
 */
public class AIPlayer {
    /**
     * board is an arraylist of the Card Class
     */
    private ArrayList<Card> board;

    /**
     * The deck is just a call of an arraylist of cards 
     */
    private Deck deck;

    /**
     * A reference which current Board is required to win
     */
    private int currentBoardRank;

    /**
     * This is the constructor for AIPlayer which loads the deck
     * shuffles the deck 
     * Deals its 4x4 board of 16cards 
     * @param deck calling the deck of cards
     */
    public AIPlayer(Deck deck) {
        this.deck = deck;
        this.deck.shuffle();
        board = deck.deal(16); 

    }

    // Method to simulate the AI making a move
    public void makeMove(ArrayList<Card> dealerCards, Loteria_Game_Load gameLoad) {
        for (Card dealerCard : dealerCards) {
            for (int i = 0; i < board.size(); i++) {

                Card card = board.get(i);
                if (card.getRank() == dealerCard.getRank() && !card.isSelected()) {
                    card.selectedCard();
                    int row = i / 4; 
                    int col = i % 4;
                    System.out.println("AI selected a card at position: [" + row + ", " + col + "]");
                    gameLoad.drawCircleOnLabel(row, col); 
                    break;
                }
            }
        }
    }


    /**
     * Setting the current rank to the currentBoardRank
     * @param rank a cursosr for which board is needed to win 
     */
    public void setCurrentBoardRank(int rank) {
        this.currentBoardRank = rank;
    }

    /**
     * 
     * @return
     */
    public int getCurrentBoardRank() {
        return currentBoardRank;
    }

    public boolean hasWon(Map<Integer, WinningCondition> winningConditions) {
        WinningCondition aiCondition = winningConditions.get(currentBoardRank);
        if (aiCondition != null && aiCondition.checkAICondition(board)) {
            return true;
        }
        return false;
    }

}

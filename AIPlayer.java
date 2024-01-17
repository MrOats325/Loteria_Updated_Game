import java.util.ArrayList;
import java.util.Map;

public class AIPlayer {
    private ArrayList<Card> board;
    private Deck deck;

    private int currentBoardRank;

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

    public void setCurrentBoardRank(int rank) {
        this.currentBoardRank = rank;
    }

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

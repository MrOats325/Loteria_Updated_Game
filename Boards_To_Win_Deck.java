import java.util.ArrayList;
import java.util.Collections;

public class Boards_To_Win_Deck{
    private ArrayList<Boards_To_Win> boards;


    public Boards_To_Win_Deck(){
        boards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck(){
        for(int i = 1; i <= 4; i++){
            boards.add(new Boards_To_Win(i)); 
        }
    }

    public void shuffle(){
        Collections.shuffle(boards);
    }

    public ArrayList<Boards_To_Win> deal(int numberOfCards) {
        ArrayList<Boards_To_Win> dealtCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (!boards.isEmpty()) {
                dealtCards.add(boards.remove(0));
            }
        }
        return dealtCards;
    }

    public boolean hasCards() {
        return !boards.isEmpty();
    }
}
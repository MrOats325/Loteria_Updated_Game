import java.util.ArrayList;
import java.util.Collections;

public class GameBoard{
    private ArrayList<Card> cards; 
    
    public GameBoard(Player[] players){
        this.cards = new ArrayList<>();
        initializeBoard(); 
    }
    //Initialize the game board 
    private void initializeBoard(){
        //Populate the cards list with Card objects 
        //For Simplicity, using the card id's
        for(int i =1; i<= 54; i++){
            cards.add(new Card(i));
        }
        Collections.shuffle(cards); 
    }
}


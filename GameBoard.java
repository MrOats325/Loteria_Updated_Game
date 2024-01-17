import java.util.ArrayList;
import java.util.Collections;

public class GameBoard{
    private ArrayList<Card> cards; //List of the Cards
    private Player[] players;  //Array of Players
    private int currentPlayerIndex; //CurrentPlayerIndex
    private boolean gameInProgress; //Flag to check if the game condition has been met 

    public GameBoard(Player[] players){
        this.players = players; 
        this.cards = new ArrayList<>();
        this.currentPlayerIndex = 0; 
        this.gameInProgress = false; 
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

    //Starts the game 
    public void startGame(){
        gameInProgress = true; 
    }

    //End the game
    public void engGame(){
        gameInProgress = false; 
    }

    
    
}


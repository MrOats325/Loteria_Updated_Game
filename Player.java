import java.util.ArrayList;
public class Player {
    private String name; 
    private int score; 
    private ArrayList<Card> cards; 

    //Constructors 
    public Player(String name){
        this.name = name; 
        this.score = 0; 
        this.cards = new ArrayList<>(); 
    }

    //Adds a card to the players collection 
    public void addCard(Card  card){
        if(card != null){
            cards.add(card); 
        }
    }

    //Increments the player's score 
    public void incrementScore(){
        score++; 
    }

    //Resets the player's score and cards 
    public void resetPlayer(){
        score = 0; 
        cards.clear(); 
    }

    //Getters and Setters 
    public String getName(){
        return name; 
    }

    public void setName(String name){
        this.name = name; 
    }

    public int getScore(){
        return score; 
    }

    public ArrayList<Card> getCards(){
        return cards; 
    }
    
    public boolean hasCard(Card card) {
        return cards.contains(card);
    }


}

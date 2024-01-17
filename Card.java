import java.awt.MediaTracker;
import javax.swing.ImageIcon; 

/**
 *  Represents a card in the Loteria game. Each card has a rank, an image, and a selection state.
 */
public class Card {
    /**
     * The rank or num of the card
     */
    private final int rank; 
    /**
     * The image of the card
     */
    private ImageIcon image; 
    /**
     * Flag to check if the card has been selected
     */
    private boolean isSelected; 

    /**
     * Constructs a new card with specified rank
     * Loads the card's images based on its rank
     * 
     * @param rank the rank of card 
     */
    public Card(int rank){
        this.rank = rank;
        String imagePath = "Loteria_Cards_Png/Loteria_" + rank + ".PNG";
        this.image = new ImageIcon(imagePath);
        if (this.image.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("Failed to load image: " + imagePath);
        }
        this.isSelected = false;  
    }

    /**
     * Marks the card as selected.
     */
    public void selectedCard(){
        isSelected = true; 
    }

    /**
     * Resets the card's selected state to false.
     */
    public void resetCard(){
        isSelected = false; 
    }

    /**
     * Returns the rank of the card.
     * 
     * @return the rank of the card.
     */
    public int getRank(){
        return rank; 
    }

    /**
     * Returns the image of the card.
     * 
     * @return the image of the card.
     */
    public ImageIcon getImage(){
        return image; 
    }

    /**
     * Checks if the card is selected.
     * 
     * @return true if the card is selected, false otherwise.
     */
    public boolean isSelected(){
        return isSelected; 
    }

}

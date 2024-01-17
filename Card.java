import java.awt.MediaTracker;

import javax.swing.ImageIcon; 

public class Card {
    //Rank or number of card
    private final int rank; 
    //image of the card
    private ImageIcon image; 
    //Flag to check if the card has been selected
    private boolean isSelected; 

    //Constructors 
    public Card(int rank){
        this.rank = rank;
        String imagePath = "../Loteria_Cards_Png/Loteria_" + rank + ".PNG";
        this.image = new ImageIcon(imagePath);
        if (this.image.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("Failed to load image: " + imagePath);
        }
        this.isSelected = false;  
    }

    //Marks the card as selected 
    public void selectedCard(){
        isSelected = true; 
    }

    //Resets the cards 
    public void resetCard(){
        isSelected = false; 
    }

    //Getters for card properties 
    public int getRank(){
        return rank; 
    }

    public ImageIcon getImage(){
        return image; 
    }

    public boolean isSelected(){
        return isSelected; 
    }

}

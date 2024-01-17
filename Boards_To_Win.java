import java.awt.MediaTracker;
import javax.swing.ImageIcon; 

public class Boards_To_Win {
    private final int rank; 
    
    private ImageIcon image;

    private boolean isSelected; 

    //Constructors
    public Boards_To_Win(int rank){
        this.rank = rank;
        String imagePath = "../Loteria_Winning_boards/" + rank + "_board.PNG";
        this.image = new ImageIcon(imagePath);

        if (this.image.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("Failed to load image: " + imagePath);
        }

        this.isSelected = false; 
    }

    public void selectedBaord(){
        isSelected = true; 
    }

    public void resetBoard(){
        isSelected = false; 
    }

    public int getRank(){
        return  rank; 
    }

    public ImageIcon getImage(){
        return image; 
    }
    
    public boolean isSelected(){
        return isSelected; 
    }

}

import java.util.ArrayList;

import javax.swing.JButton;

public class FourSpacesTopWinCodition implements WinningCondition{

    @Override
    public boolean checkCondition(JButton[][] buttons) {
        // Ensure the top row of buttons are all disabled
        for (int col = 0; col < buttons[0].length; col++) {
            if (buttons[0][col].isEnabled()) {
                return false; 
            }
        }
        return true;
    }

    @Override
    public boolean checkAICondition(ArrayList<Card> aiBoard) {
        // Indices for the top row in a 4x4 grid are 0, 1, 2, 3
        int[] topRowIndices = {0, 1, 2, 3};
        for (int index : topRowIndices) {
            if (!aiBoard.get(index).isSelected()) {
                return false;
            }
    }
    return true; 
    }
    
    
}

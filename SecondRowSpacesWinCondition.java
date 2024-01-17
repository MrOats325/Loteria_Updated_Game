import java.util.ArrayList;

import javax.swing.JButton;

public class SecondRowSpacesWinCondition implements WinningCondition {

    @Override
    public boolean checkCondition(JButton[][] buttons) {
        // Ensure the second row of buttons are all disabled
        for (int col = 0; col < buttons[1].length; col++) {
            if (buttons[1][col].isEnabled()) {
                return false; 
            }
        }
        return true; 
    }

    @Override
    public boolean checkAICondition(ArrayList<Card> aiBoard) {
        // Indices for the second row in a 4x4 grid are 4, 5, 6, 7
        int[] secondRowIndices = {4, 5, 6, 7};
        for (int index : secondRowIndices) {
            if (!aiBoard.get(index).isSelected()) {
                return false; 
            }
        }
        return true; 
    }
}

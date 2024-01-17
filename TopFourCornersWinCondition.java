import java.util.ArrayList;

import javax.swing.JButton;

public class TopFourCornersWinCondition implements WinningCondition {

    @Override
    public boolean checkCondition(JButton[][] buttons) {
        // Check if all four box top  are disabled (pressed)
        return !buttons[0][0].isEnabled() && 
               !buttons[0][1].isEnabled() &&
               !buttons[1][0].isEnabled() &&
               !buttons[1][0].isEnabled();
    }

    @Override
    public boolean checkAICondition(ArrayList<Card> aiBoard) {
        // Indices for the second column in a 4x4 grid are 1, 5, 9, 13
        int[] secondColumnIndices = {1, 5, 9, 13};
        for (int index : secondColumnIndices) {
            if (!aiBoard.get(index).isSelected()) {
                return false; // If any card in the second column is not selected, return false
            }
        }
        return true; // All cards in the second column are selected, winning condition met
    }
    
}

import java.util.ArrayList;

import javax.swing.JButton;

class FourCornersWinCondition implements WinningCondition {

    @Override
    public boolean checkCondition(JButton[][] buttons) {
        // Check if all four corners are disabled (pressed)
        return !buttons[0][0].isEnabled() && 
               !buttons[0][buttons[0].length - 1].isEnabled() &&
               !buttons[buttons.length - 1][0].isEnabled() &&
               !buttons[buttons.length - 1][buttons[0].length - 1].isEnabled();
    }

    public boolean checkAICondition(ArrayList<Card> aiBoard) {
        // Indices for the second column in a 4x4 grid are 0,3,12,15
        int[] secondColumn = {0,3,12,15};
        for (int index : secondColumn) {
            if (!aiBoard.get(index).isSelected()) {
                return false;
            }
        }
        return true; 
    }
}

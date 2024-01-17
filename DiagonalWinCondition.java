import java.util.ArrayList;

import javax.swing.JButton;

public class DiagonalWinCondition implements WinningCondition {

    @Override
    public boolean checkCondition(JButton[][] buttons) {

        for (int i = 0; i < buttons.length; i++) {
        if (buttons[buttons.length - 1 - i][i].isEnabled()) {
            return false;
        }
    }
    return true;
}

    @Override
    public boolean checkAICondition(ArrayList<Card> aiBoard) {
        // Indices for the diagonal from bottom-left to top-right in a 4x4 grid are 12, 9, 6, 3
        int[] diagonalIndices = {12, 9, 6, 3};
        for (int index : diagonalIndices) {
            if (!aiBoard.get(index).isSelected()) {
                return false; // If any card in the diagonal is not selected, return false
            }
        }
        return true; // All cards in the diagonal are selected, winning condition met
    }

    
}

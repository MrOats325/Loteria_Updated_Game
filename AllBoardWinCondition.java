import java.util.ArrayList;

import javax.swing.JButton;

public class AllBoardWinCondition implements WinningCondition{

    @Override
    public boolean checkCondition(JButton[][] buttons) {
        // Ensure the second row of buttons are all disabled
        for (int row = 0; row < buttons.length; row++) {
            for(int col = 0; col < buttons[row].length; col++)
            if (buttons[row][col].isEnabled()) {
                return false; // If any button in the second row is enabled, return false
            }
        }
        return true; // All second row buttons are disabled, winning condition met
    }

    @Override
    public boolean checkAICondition(ArrayList<Card> aiBoard) {
        // Indices for the second column in a 4x4 grid are 1, 5, 9, 13
        int[] allBoard = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for (int index : allBoard) {
            if (!aiBoard.get(index).isSelected()) {
                return false; // If any card in the second column is not selected, return false
            }
        }
        return true; // All cards in the second column are selected, winning condition met
    }
    
}

import java.util.ArrayList;

import javax.swing.JButton;

interface WinningCondition {
    boolean checkCondition(JButton[][] buttons);
    boolean checkAICondition(ArrayList<Card> aiBoard) ;
}

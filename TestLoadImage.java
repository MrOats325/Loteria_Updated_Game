import java.awt.FlowLayout;

import javax.swing.*;

public class TestLoadImage {
    public static void main(String[] args) {
        ImageIcon image = new ImageIcon("../Loteria_Winning_boards/" + 2 + "_board.PNG"); 
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 300);
        JLabel label = new JLabel(image);
        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


import java.awt.Font;
/**
 * The title screen.
 * 
 * @author MrOats325
 * @version 2022.05.04
 */

public class DisplayScreen extends JPanel {
	/** play button */
	private JButton button;
	private static final Color MELLOW_RED = new Color(219, 60, 60);
	//private static final Font BUTTON_FONT = new Font("Comic Sans", Font.BOLD, 25);
	//private static final Border BUTTON_BORDER = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


	public DisplayScreen() {
		setLayout(new GridLayout(1, 1));
		button = new JButton("Welcome! Press To Play");
		button.setFont(new Font("Century", Font.BOLD, 40));
		button.setIcon(new ImageIcon("LoteriaPDF/Loteria_loadingscreen.png"));

		button.addActionListener(e -> LoteriaDriver.changeFrame(2));
		button.setBackground(MELLOW_RED);
		add(button);
	}
}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Loteria_Instructions extends JPanel implements ActionListener {
	private static final Color REAL_RED = new Color(219, 0, 0);
	private JLabel instructionsLabel;
	private JButton playGameButton;

	public Loteria_Instructions() {
		//instructionsLabel = new JLabel(new ImageIcon("LoteriaPDF/instructions.png"));
		instructionsLabel.setOpaque(true);
		instructionsLabel.setBackground(REAL_RED);

		playGameButton = new JButton("Play Game");
		playGameButton.setFont(new Font("Comic Sans", Font.BOLD, 25));
		playGameButton.setBounds(315, 362, 235, 105);
		playGameButton.addActionListener(this);
		playGameButton.setBackground(REAL_RED);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(playGameButton);
		buttonPanel.setOpaque(false);

		setLayout(new BorderLayout());
		add(instructionsLabel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		setBackground(REAL_RED);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playGameButton) {
			LoteriaDriver.changeFrame(4);
		}
	}
}
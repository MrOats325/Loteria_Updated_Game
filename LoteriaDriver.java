import javax.swing.JFrame;

public class LoteriaDriver {
	/** Displays Title Screen */
	private static JFrame displayFrame;

	/** Displays Button Screen */
	private static JFrame buttonFrame;

	/** Displays The Instructions */
	private static JFrame instructionsFrame;

	/** Displays The Game */
	private static JFrame gameFrame;

	/** Displays the Winning Screen */
	private static JFrame winFrame;

	private static final int FRAME_WIDTH = 850;
	private static final int FRAME_HEIGHT = 550;
	private static final int GAME_FRAME_WIDTH = 1450;
	private static final int GAME_FRAME_HEIGHT = 800;

	/**
	 * Given an int, case to the corresponding JFrame (1, 2, 3, or 4).
	 */
	public static void changeFrame(int num) {
		displayFrame.setVisible(false);
		buttonFrame.setVisible(false);
		instructionsFrame.setVisible(false);
		gameFrame.setVisible(false);
		winFrame.setVisible(false);

		switch (num) {
		case 1:
			displayFrame.setVisible(true);
			displayFrame.setLocation(buttonFrame.getX(), buttonFrame.getY());
			displayFrame.setSize(buttonFrame.getWidth(), buttonFrame.getHeight());
			break;
		case 2:
			buttonFrame.setVisible(true);
			buttonFrame.setLocation(displayFrame.getX(), displayFrame.getY());
			buttonFrame.setSize(displayFrame.getWidth(), displayFrame.getHeight());
			break;
		case 3:
			instructionsFrame.setVisible(true);
			instructionsFrame.setLocation(buttonFrame.getX(), buttonFrame.getY());
			instructionsFrame.setSize(buttonFrame.getWidth(), buttonFrame.getHeight());
			break;
		case 4:
			gameFrame.setVisible(true);
			break;
		case 5:
			instructionsFrame.setVisible(true);
			winFrame.setVisible(true);
			winFrame.setLocation(buttonFrame.getX(), buttonFrame.getY());
			winFrame.setSize(buttonFrame.getWidth(), buttonFrame.getHeight());
			break;
		default:
			throw new IllegalArgumentException("Invalid frame number: " + num);
		}
	}

	/**
	 * Set basic properties for each JFrame.
	 * 
	 * @param jf a JFrame
	 */
	private static void setFrame(JFrame jf) {
		jf.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private static void gameFrame(JFrame jf) {
		jf.setSize(GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		displayFrame = new JFrame("Lotreia");
		setFrame(displayFrame);
		displayFrame.setContentPane(new DisplayScreen());
        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		displayFrame.setVisible(true);

		buttonFrame = new JFrame("Loteria Main Screen");
		setFrame(buttonFrame);
		buttonFrame.setContentPane(new Main_Menu_Loteria());
		buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonFrame.setVisible(false);

		instructionsFrame = new JFrame("Instruction Screen");
		setFrame(instructionsFrame);
		//instructionsFrame.setContentPane(new Loteria_Instructions());
        instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instructionsFrame.setVisible(false);

		gameFrame = new JFrame("Loteria Game");
		gameFrame(gameFrame);
		gameFrame.setContentPane(new Loteria_Game_Load());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(false);

		winFrame = new JFrame("Winner");
		setFrame(winFrame);
		//winFrame.setContentPane(new Winner_Screen());
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winFrame.setVisible(false);

	}
}

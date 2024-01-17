import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Loteria_Game_Load extends JPanel implements ActionListener {
    //Colors 
    private static final Color mellow_red = new Color(219, 60, 60); 

    //Board
    private ArrayList<Card> board;
    private JButton[][] buttons;
    
    //Loteria Button
    private JButton buttonToWin;
    
    //Dealer 
    private Deck deck; 
    private Deck deck_dealer;
    private JLabel winningConditionTitle; 
    private Card currentDealerCard;

    //Ai_1
    private AIPlayer aiPlayer;
    private JLabel[][] aiBoardLabels;
    private JLabel aiPlayerLabel;

    //Ai_2
    private AIPlayer aiPlayer_2;
    private JLabel[][] aiBoardLabels_2;
    private JLabel aiPlayerLabel_2;

    // Dealer
    private JLabel changer;
    private ArrayList<Card> dealerCards; 
    private Timer timer; 
    
    // Way to Win
    private JLabel winningCondition;
    private Boards_To_Win currentBoardCard;
    private Map<Integer, WinningCondition> winningConditions;
    private Boards_To_Win_Deck bo_deck;



    public Loteria_Game_Load() {
        deck = new Deck();
        deck.shuffle(); 
        board = deck.deal(16); 

        deck_dealer = new Deck();
        deck_dealer.shuffle();

        aiPlayer = new AIPlayer(new Deck()); 
        aiPlayer_2 = new AIPlayer(new Deck()); 


        winningConditions = new HashMap<>();
        winningConditions.put(1, new DiagonalWinCondition()); 
        winningConditions.put(2, new FourCornersWinCondition()); 
        winningConditions.put(3, new TopFourCornersWinCondition()); 
        winningConditions.put(4, new AllBoardWinCondition()); 
        winningConditions.put(5, new SecondRowSpacesWinCondition()); 
        winningConditions.put(6, new SecondColumnSpacesWinCondition());

        setLayout(null); 

        initializeBoardButtons(); 
        initializeDealer();
        initializeAIBoardLabels();
        initializeBoardWayToWin();
        initializeButtonToWin();
        initializeBackgroundLabels();

    }

    public void actionPerformed(ActionEvent e) {
        checkWinningCondition();
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                if (e.getSource() == buttons[row][col]) {
                    Card clickedCard = board.get(row * 4 + col);
                    if (matchesDealerCard(clickedCard)) {
                        clickedCard.selectedCard();
                        buttons[row][col].setEnabled(false); 
                    }

                }
            }
        }
    }

    private void initializeBackgroundLabels() {
        int posX = 0;
        int posY = 0; 
        int labelsWidth = 480;
        int labelsHeight = 800; 

        class CustomLabel extends JLabel {
            private Color circleColor;
            private boolean drawCircle;
    
            public CustomLabel(Color backgroundColor, Color circleColor, boolean drawCircle) {
                setOpaque(true);
                setBackground(backgroundColor);
                this.circleColor = circleColor;
                this.drawCircle = drawCircle;
            }
    
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (drawCircle) {
                    g.setColor(circleColor);
                    int diameter = Math.min(getWidth(), getHeight()) / 2; 
                    int x = (getWidth() - diameter) / 2;
                    int y = (getHeight() - diameter) / 2;
                    g.fillOval(x , y , diameter, diameter);
                }
            }
        }

    CustomLabel greenLabel = new CustomLabel(new Color(0, 128, 0), Color.BLACK, false);
    greenLabel.setBounds(posX, posY, labelsWidth, labelsHeight);
    add(greenLabel);

    // White label with brown circle
    CustomLabel whiteLabel = new CustomLabel(Color.WHITE, new Color(139, 69, 19), true); // Brown circle
    whiteLabel.setBounds(posX + labelsWidth, posY, labelsWidth, labelsHeight);
    add(whiteLabel);

    // Red label without circle
    CustomLabel redLabel = new CustomLabel(new Color(206, 17, 38), Color.BLACK, false); // No circle
    redLabel.setBounds(posX + labelsWidth * 2, posY, labelsWidth, labelsHeight);
    add(redLabel);

    }

    private void initializeButtonToWin() {
        buttonToWin = new JButton();
        int posX = 50;
        int posY = 200; 
        int buttonWidth = 250;
        int buttonHeight = 70; 
        buttonToWin.setBounds(posX, posY, buttonWidth, buttonHeight);

        buttonToWin.setText("LOTERIA!");
        buttonToWin.setFont(new Font("Century", Font.BOLD, 40));
        buttonToWin.setBackground(mellow_red);
        buttonToWin.addActionListener(e -> checkWinningCondition()); 

        add(buttonToWin);
    }

    private void initializeBoardButtons() {
        buttons = new JButton[4][4];
        int posX = 465; 
        int posY = 20; 
        int xOffset = 125; 
        int yOffset = 170; 

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Card card = board.get(row * 4 + col);
                JButton button = new JButton(new ImageIcon(card.getImage().getImage()));
                button.setBounds(posX + xOffset * col, posY + yOffset * row, 130, 150);
                button.setBackground(mellow_red);
                button.setBorderPainted(false);
                button.addActionListener(this);
                buttons[row][col] = button;
                add(button);
            }
        }
    }

    private void initializeBoardWayToWin() {
        winningCondition = new JLabel();
        winningCondition.setBounds(50, 400, 200, 270);
        winningCondition.setForeground(mellow_red);

        winningConditionTitle = new JLabel();
        winningConditionTitle.setBounds(40, 650, 270, 70);
        winningConditionTitle.setText("WAY TO WIN");
        winningConditionTitle.setFont(new Font("Century", Font.BOLD, 35));

        bo_deck = new Boards_To_Win_Deck();
        bo_deck.shuffle();

        // Deal the next board card and store it
        currentBoardCard = bo_deck.deal(1).get(0);
        aiPlayer.setCurrentBoardRank(currentBoardCard.getRank());

        // Set the icon for the winningCondition label using currentBoardCard's image
        winningCondition.setIcon(new ImageIcon(currentBoardCard.getImage().getImage()));
        add(winningCondition);
        add(winningConditionTitle);

        revalidate();
        repaint();

    }

    private void initializeAIBoardLabels() {
        aiBoardLabels = new JLabel[4][4];
        aiBoardLabels_2 = new JLabel[4][4];

        int posX = 1100; 
        int posY = 20;
        int xOffset = 50;
        int yOffset = 70;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                JLabel label = new JLabel();
                label.setBounds(posX + xOffset * col, posY + yOffset * row, 50, 70);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                add(label);
                aiBoardLabels[row][col] = label;
            }
        }



        aiPlayerLabel = new JLabel();
        aiPlayerLabel.setBounds(posX , 300, 120, 60);
        aiPlayerLabel.setText("Player 2");
        aiPlayerLabel.setFont(new Font("Century", Font.BOLD, 25));
        add(aiPlayerLabel);


        aiPlayerLabel_2 = new JLabel();
        aiPlayerLabel_2.setBounds(posX , 637, 120, 60);
        aiPlayerLabel_2.setText("Player 3");
        aiPlayerLabel_2.setFont(new Font("Century", Font.BOLD, 25));
        add(aiPlayerLabel_2);
    }

    void drawCircleOnLabel(int row, int col) {
        JLabel newLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                g.drawOval(10, 10, width - 20, height - 20);
                g.setColor(mellow_red);
                g.fillOval(10, 10, width - 20, height - 20);
            }
        };

        // Copy the bounds and properties from the old label
        JLabel oldLabel = aiBoardLabels[row][col];
        newLabel.setBounds(oldLabel.getBounds());
        newLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newLabel.setOpaque(true);
        newLabel.setBackground(Color.WHITE);


        remove(oldLabel);
        add(newLabel);
        aiBoardLabels[row][col] = newLabel;
        newLabel.repaint();
    }

    private void initializeDealer() {
        dealerCards = new ArrayList<>();
        changer = new JLabel();
        changer.setBounds(100, 20, 130, 150);
        changer.setForeground(mellow_red);
        add(changer);

        updateDealerCard(); 

        Timer initialDelayTimer = new Timer(2500, e -> {
            // Start the regular update timer after the initial delay
            timer = new Timer(1500, ev -> updateDealerCard());
            timer.start();
        });
        initialDelayTimer.setRepeats(false); 
        initialDelayTimer.start();

        revalidate();
        repaint();
    }

    private void updateDealerCard() {
        if (deck_dealer.hasCards()) {
            currentDealerCard = deck_dealer.deal(1).get(0);
            dealerCards.add(currentDealerCard);
            changer.setIcon(new ImageIcon(currentDealerCard.getImage().getImage()));
            changer.repaint(); 

            aiPlayer.makeMove(dealerCards, this);
            if (aiPlayer.hasWon(winningConditions)) {
                showWinningScreen("Player 2 Won!");
                timer.stop(); 
            }

        }
    }

    private boolean matchesDealerCard(Card card) {
        for (Card dealerCard : dealerCards) {
            if (card.getRank() == dealerCard.getRank()) {
                return true; 
            }
        }
        return false; 
    }

    private void checkWinningCondition() {
        if (winningConditions.get(currentBoardCard.getRank()).checkCondition(buttons)) {
            showWinningScreen("Player Won!");
            return;
        }
        if (aiPlayer.hasWon(winningConditions)) {
            showWinningScreen("Player 2 Won!");
            return;
        }
    }

    private void showWinningScreen(String winnerMessage) {
        // Stop the timer and any other ongoing processes
        if (timer != null) {
            timer.stop();
        }
    
        // Create a new modal dialog for the winning screen
        JDialog winningDialog = new JDialog();
        winningDialog.setTitle("Game Over");
        winningDialog.setSize(400, 200);
        winningDialog.setLayout(new BorderLayout());
        winningDialog.setModal(true); // Set the dialog as modal
        winningDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    
        // Create and add a label with the winning message
        JLabel winLabel = new JLabel(winnerMessage, SwingConstants.CENTER);
        winningDialog.add(winLabel, BorderLayout.CENTER);
    
        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
    
        // Restart button
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            winningDialog.dispose();
            restartGame();
        });
        buttonPanel.add(restartButton);
    
        // Quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(quitButton);
    
        // Add the panel to the dialog
        winningDialog.add(buttonPanel, BorderLayout.SOUTH);
    
        // Display the dialog
        winningDialog.setLocationRelativeTo(null); // Center on screen
        winningDialog.setVisible(true);
    }
    

    private void restartGame() {
        // Reset and shuffle decks
        deck = new Deck();
        deck.shuffle();
        deck_dealer = new Deck();
        deck_dealer.shuffle();

        // Reset the board for the player
        board = deck.deal(16);
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setIcon(new ImageIcon(board.get(row * 4 + col).getImage().getImage()));
                buttons[row][col].setEnabled(true);
            }
        }

        // Reset the AI player's board
        // Reset the AI player's board
        aiPlayer = new AIPlayer(new Deck());
        aiPlayer.setCurrentBoardRank(bo_deck.deal(1).get(0).getRank());
        resetAIBoardLabels(aiBoardLabels); // Reset AI board labels

        
            // Remove existing AI board labels from the panel and re-initialize
        for (int row = 0; row < aiBoardLabels.length; row++) {
         for (int col = 0; col < aiBoardLabels[row].length; col++) {
            remove(aiBoardLabels[row][col]);
         }
            }
        // Reset winning conditions
        winningCondition.setIcon(new ImageIcon(bo_deck.deal(1).get(0).getImage().getImage()));

        // Reset the dealer
        dealerCards.clear();
        changer.setIcon(null);

        // Restart the timer
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1500, ev -> updateDealerCard());
        timer.start();

        // Remove existing AI board labels from the panel
        for (int row = 0; row < aiBoardLabels.length; row++) {
            for (int col = 0; col < aiBoardLabels[row].length; col++) {
                remove(aiBoardLabels[row][col]);
            }
        }
        initializeBackgroundLabels();
        initializeAIBoardLabels();

        currentBoardCard = bo_deck.deal(1).get(0);
        aiPlayer.setCurrentBoardRank(currentBoardCard.getRank());
        winningCondition.setIcon(new ImageIcon(currentBoardCard.getImage().getImage()));

        bringAIToFront();

        // Refresh the UI
        revalidate();
        repaint();

    }
    private void resetAIBoardLabels(JLabel[][] aiBoardLabels) {
        int posX = 1100; // Adjust these values based on your layout
        int posY = 20;
        int xOffset = 50;
        int yOffset = 70;
    
        for (int row = 0; row < aiBoardLabels.length; row++) {
            for (int col = 0; col < aiBoardLabels[row].length; col++) {
                JLabel label = aiBoardLabels[row][col];
    
                // Remove any custom painting or icons from the label
                label.setIcon(null);
                label.repaint();
    
                // Reset bounds and properties
                label.setBounds(posX + xOffset * col, posY + yOffset * row, 50, 70);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
            }
        }
    }

    private void bringAIToFront() {
        for (JLabel[] labelRow : aiBoardLabels) {
            for (JLabel label : labelRow) {
                setComponentZOrder(label, 0); // Bringing each AI label to front
            }
        }
    }
    

}
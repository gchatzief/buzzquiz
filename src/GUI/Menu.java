package GUI;

import GUI.Images.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/** This class presents the menu of the game that players will choose how many rounds,questions and the name of the players. */
public class Menu implements ActionListener {

    private final JFrame menuFrame;

    private int numberOfRounds;
    private int questionNumber;
    private int numberOfPlayers;
    private boolean isSubmitted1;
    private boolean isSubmitted2;
    private final String[] playersName = {"",""};

    private final JPanel leftPanel;
    private final JLabel textRounds;
    private final JComboBox<String> rounds;
    private final JLabel textQuestionsRound;
    private final JComboBox<String> questionsRound;

    private final JPanel topPanel;
    private final JLabel menuLabel;

    private final JPanel centerPanel;
    private final JPanel topCenterPanel;
    private final JPanel bottomCenterPanel;
    private final JButton singlePlayer;
    private final JButton submit1;
    private final JTextField name1;

    private final JPanel rightPanel;
    private final JPanel topRightPanel;
    private final JPanel bottomRightPanel;
    private final JButton multiPlayer;
    private final JButton submit2;
    private final JTextField name2;

    private final JPanel bottomPanel;
    private final JButton nextButton;

    /** The constructor initialize the GUI components.
     * @param menuFrame It gets the previews frame athat will reshape it. */
    public Menu(JFrame menuFrame) throws FileNotFoundException {

        menuFrame.getContentPane().removeAll();
        menuFrame.repaint();

        this.menuFrame = menuFrame;

        // Initialise all to 1 if the user didn't choose anything  on menu.
        numberOfRounds = 1;
        questionNumber = 1;
        numberOfPlayers = 1;

        // These variables represent if the submit buttons have been pressed.
        isSubmitted1=false;
        isSubmitted2=false;

        leftPanel = new JPanel();
        textRounds = new JLabel();
        textQuestionsRound = new JLabel();
        String[] numbers = {"1", "2", "3", "4", "5"}; // The number of rounds and question in each round the user can choose.
        rounds = new JComboBox<>(numbers);
        questionsRound = new JComboBox<>(numbers);
        leftPanel();

        topPanel = new JPanel();
        menuLabel = new JLabel();
        topPanel();

        centerPanel = new JPanel();
        topCenterPanel = new JPanel();
        bottomCenterPanel = new JPanel();
        singlePlayer = new JButton();
        submit1 = new JButton();
        name1 = new JTextField("Write your name.");
        centerPanel();

        rightPanel = new JPanel();
        topRightPanel = new JPanel();
        bottomRightPanel = new JPanel();
        multiPlayer = new JButton();
        submit2 = new JButton();
        name2 = new JTextField("Write your name.");
        rightPanel();

        bottomPanel = new JPanel();
        nextButton = new JButton();
        bottomPanel();

        this.menuFrame.add(leftPanel, BorderLayout.WEST);
        this.menuFrame.add(topPanel, BorderLayout.NORTH);
        this.menuFrame.add(centerPanel, BorderLayout.CENTER);
        this.menuFrame.add(rightPanel, BorderLayout.EAST);
        this.menuFrame.add(bottomPanel, BorderLayout.SOUTH);

        this.menuFrame.setVisible(true);

    }

    /** Sets the leftPanel which contains the combo boxes. */
    private void leftPanel(){
        leftPanel.setBackground(new Color(104,187,227));
        leftPanel.setOpaque(true);
        leftPanel.setPreferredSize(new Dimension(350,0));

        textRounds.setText("Number of rounds.");
        textRounds.setFont(new Font("Serif", Font.BOLD, 17));
        leftPanel.add(textRounds);

        rounds.addActionListener( e -> {
            if (e.getSource() == rounds){
                if (rounds.getSelectedItem() == "1")
                    numberOfRounds=1;
                else if (rounds.getSelectedItem() == "2")
                    numberOfRounds=2;
                else if (rounds.getSelectedItem() == "3")
                    numberOfRounds=3;
                else if (rounds.getSelectedItem() == "4")
                    numberOfRounds=4;
                else if (rounds.getSelectedItem() == "5")
                    numberOfRounds=5;
            }
        });

        leftPanel.add(rounds);

        textQuestionsRound.setText("Number of questions in each round.");
        textQuestionsRound.setFont(new Font("Serif", Font.BOLD, 17));
        leftPanel.add(textQuestionsRound);

        questionsRound.addActionListener( e -> {
            if (e.getSource() == questionsRound){
                if (questionsRound.getSelectedItem() == "1")
                    questionNumber=1;
                else if (questionsRound.getSelectedItem() == "2")
                    questionNumber=2;
                else if (questionsRound.getSelectedItem() == "3")
                    questionNumber=3;
                else if (questionsRound.getSelectedItem() == "4")
                    questionNumber=4;
                else if (questionsRound.getSelectedItem() == "5")
                    questionNumber=5;
            }
        });

        leftPanel.add(questionsRound);

    }

    /** Sets the topPanel which contains the logo of menu. */
    private void topPanel(){
        topPanel.setBackground(Color.CYAN);
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(0,150));

        if (Resource.getURL("Menu.png") != null)
            menuLabel.setIcon(new ImageIcon(Resource.getURL("Menu.png")));
        menuLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(menuLabel);
    }

    /** Sets the centerPanel which contains the single player button and the submit button. */
    private void centerPanel(){
        //centerPanel.setBackground(Color.GREEN);
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());

        singlePlayer.setBounds(10,10,270,60);
        singlePlayer.setText("Single-Player");
        singlePlayer.setFont(new Font("Serif", Font.BOLD, 20));
        singlePlayer.setForeground(Color.BLACK);
        singlePlayer.setBackground(new Color(69,43,217));
        singlePlayer.addActionListener(e -> {
            singlePlayer.setEnabled(false);
            multiPlayer.setEnabled(false);
            submit1.setVisible(true);
            name1.setVisible(true);
            this.menuFrame.setVisible(true);
            numberOfPlayers = 1;
        });

        topCenterPanel.setBackground(new Color(104,187,227));
        topCenterPanel.setOpaque(true);
        topCenterPanel.setLayout(new FlowLayout());
        topCenterPanel.setPreferredSize(new Dimension(0,150));

        topCenterPanel.add(singlePlayer);

        bottomCenterPanel.setBackground(new Color(104,187,227));
        bottomCenterPanel.setOpaque(true);
        bottomCenterPanel.setLayout(new FlowLayout());
        bottomCenterPanel.setPreferredSize(new Dimension(0,350));

        submit1.setVisible(false);
        submit1.setText("Submit");
        submit1.setPreferredSize(new Dimension(100,20));
        submit1.addActionListener(this);

        name1.setVisible(false);

        bottomCenterPanel.add(submit1);
        bottomCenterPanel.add(name1);


        centerPanel.add(topCenterPanel, BorderLayout.NORTH);
        centerPanel.add(bottomCenterPanel, BorderLayout.SOUTH);

    }

    /** Sets the rightPanel which contains the multi player button and the submit button. */
    private void rightPanel(){
        //rightPanel.setBackground(new Color(104,187,227));
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(500,0));

        multiPlayer.setBounds(10,10,270,60);
        multiPlayer.setText("Multi-Player");
        multiPlayer.setFont(new Font("Serif", Font.BOLD, 20));
        multiPlayer.setForeground(Color.BLACK);
        multiPlayer.setBackground(new Color(69,43,217));
        multiPlayer.addActionListener(e -> {
            singlePlayer.setEnabled(false);
            multiPlayer.setEnabled(false);
            name1.setVisible(true);
            name2.setVisible(true);
            submit1.setVisible(true);
            submit2.setVisible(true);
            this.menuFrame.setVisible(true);
            numberOfPlayers=2;

        });

        topRightPanel.setBackground(new Color(104,187,227));
        topRightPanel.setOpaque(true);
        topRightPanel.setLayout(new FlowLayout());
        topRightPanel.setPreferredSize(new Dimension(0,140));

        topRightPanel.add(multiPlayer);

        bottomRightPanel.setBackground(new Color(104,187,227));
        bottomRightPanel.setOpaque(true);
        bottomRightPanel.setLayout(new FlowLayout());
        bottomRightPanel.setPreferredSize(new Dimension(0,350));

        submit2.setVisible(false);
        submit2.setText("Submit");
        submit2.setPreferredSize(new Dimension(100,20));
        submit2.addActionListener(this);

        name2.setVisible(false);

        bottomRightPanel.add(submit2);
        bottomRightPanel.add(name2);


        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        rightPanel.add(bottomRightPanel, BorderLayout.SOUTH);
    }

    /** Sets the bottomPanel which contains the next button to start the game. The game will only start
     * if the submit button is pressed (in single player the one, in multi player both). */
    private void bottomPanel(){
        //bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setPreferredSize(new Dimension(0,70));

        if (Resource.getURL("Next.png") != null)
            nextButton.setIcon(new ImageIcon(Resource.getURL("Next.png")));
        nextButton.setBackground(new Color(69,43,217));
        nextButton.setBorder(BorderFactory.createLineBorder(new Color(69,43,217)));
        nextButton.addActionListener(e -> {
            if (e.getSource() == nextButton) {
                // When 1 player is playing it checks only thw player 1 submit button.
                if (numberOfPlayers == 1) {
                    if (isSubmitted1) {
                        try {
                            new Play(this.menuFrame, numberOfRounds, questionNumber, numberOfPlayers, playersName);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                    // When 2 players are playing it checks both submit buttons.
                }else if (numberOfPlayers == 2) {
                    if (isSubmitted1 && isSubmitted2) {
                        try {
                            new Play(this.menuFrame, numberOfRounds, questionNumber, numberOfPlayers, playersName);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                }
            }else {nextButton.setText("Error. Check again the values!");}
        });

        bottomPanel.add(nextButton);

    }

    /** The action listener of submit buttons sets the names of players and give the "ok" to continue the game
     * when the names are set.
     * @param e The action event. */
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submit1){
            playersName[0] = String.valueOf(name1.getText());
            submit1.setEnabled(false);
            isSubmitted1 = true;
        }else if (e.getSource() == submit2){
            playersName[1] = String.valueOf(name2.getText());
            submit2.setEnabled(false);
            isSubmitted2 = true;
        }
    }

}

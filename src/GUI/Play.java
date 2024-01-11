package GUI;

import GUI.Images.Resource;
import MainClasses.Game;
import MainClasses.NextQuestion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** This class contains the "main" GUI that the player or players will start to play. It reform the frame
 * depend on which type of round they are playing.*/
public class  Play implements ActionListener {

    private final Game game;

    // The frame splits in 3 pieces.
    private final JFrame playFrame;

    // topPanel contains : the buttons for betting round, the description of the round with the number of round
    // and questions in each round, the image of a question and the count down timer for Stop The Timer round.
    private final JPanel topPanel;
    private final JPanel countDown;
    private final JLabel timer;
    private final JPanel roundPanel;
    private final JLabel roundLabel;
    private final JPanel imagePanel;
    private final JLabel imageLabel;
    private final JPanel bettingPanel;
    private final JButton b250;
    private final JButton b500;
    private final JButton b750;
    private final JButton b1000;

    // middlePanel contains : the questions.
    private final JPanel middlePanel;
    private final JLabel question;

    // bottomPanel contains :  the scores and the possible answers of questions.
    private final JPanel bottomPanel;
    private final JPanel lPointsPanel;
    private final JPanel rPointsPanel;
    private final JLabel lPointsLabel;
    private final JLabel rPointsLabel;

    private final JPanel answersPanel;
    private final JButton answerA;
    private final JButton answerB;
    private final JButton answerC;
    private final JButton answerD;

    boolean isPlayer1 = false; // Variable that says if players answered the question.
    boolean isPlayer2 = false;
    boolean isBet1 = false; // Variables that says if players has bet.
    boolean isBet2 = false;
    boolean cantPressThatWhileBetting1 = true; // Variables that prevents the players answer a question before they bet.
    boolean cantPressThatWhileBetting2 = true;


    private final String[] userAnswer = {"",""}; // In two players mode, this array saves the answers of players.

    /** The constructor initialize the GUI components.
     * @param playFrame : is the frame from previous class which this class will use again.
     * @param rounds : is the number of total rounds.
     * @param roundQuestions : is the number of question in each round.
     * @param numberOfPlayers : is the number of players that they are playing.
     * @param playersName : contains the names of players. */
    public Play(JFrame playFrame, int rounds, int roundQuestions, int numberOfPlayers, String[] playersName) throws FileNotFoundException {

        playFrame.getContentPane().removeAll();
        playFrame.repaint();

        this.playFrame=playFrame;
        this.playFrame.requestFocus();

        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new JPanel();
        basePanels();

        roundPanel = new JPanel();
        roundLabel = new JLabel();
        imagePanel = new JPanel();
        imageLabel = new JLabel();
        bettingPanel = new JPanel();
        b250= new JButton("250");
        b500= new JButton("500");
        b750= new JButton("750");
        b1000= new JButton("1000");
        countDown = new JPanel();
        timer = new JLabel();
        topPanel();

        question = new JLabel();
        middlePanel();

        answersPanel = new JPanel();
        answerA = new JButton();
        answerB = new JButton();
        answerC = new JButton();
        answerD = new JButton();
        lPointsPanel = new JPanel();
        rPointsPanel = new JPanel();
        lPointsLabel = new JLabel();
        rPointsLabel = new JLabel();
        BottomPanel(numberOfPlayers);

        this.playFrame.add(topPanel, BorderLayout.NORTH);
        this.playFrame.add(middlePanel, BorderLayout.CENTER);
        this.playFrame.add(bottomPanel, BorderLayout.SOUTH);

        this.playFrame.setVisible(true);

        setKeyWords();

        game = new Game(this, rounds, roundQuestions, numberOfPlayers, playersName);

    }

    /** Set the three base panels (top, middle, bottom panels) */
    private void basePanels()
    {
        //topPanel.setBackground(Color.CYAN);
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(0,300));

        //middlePanel.setBackground(new Color(43,26,142));
        middlePanel.setLayout(new BorderLayout());
        middlePanel.setPreferredSize(new Dimension(0,100));

        //bottomPanel.setBackground(Color.YELLOW);
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(0,300));
    }

    /** Add components to topPanel */
    private void topPanel()
    {
        //countDown.setBackground(Color.BLACK);
        countDown.setOpaque(false);
        countDown.setLayout(new BorderLayout());
        countDown.setPreferredSize(new Dimension(130,0));
        topPanel.add(countDown, BorderLayout.EAST);

        timer.setBackground(new Color(104,187,227));
        timer.setText("");
        timer.setHorizontalAlignment(SwingConstants.CENTER);
        timer.setPreferredSize(new Dimension(50,100));
        timer.setOpaque(true);
        countDown.add(timer, BorderLayout.NORTH);

        //bettingPanel.setBackground(Color.BLACK);
        bettingPanel.setOpaque(false);
        bettingPanel.setLayout(new GridLayout(4,1,5,5));
        bettingPanel.setPreferredSize(new Dimension(150,0));
        topPanel.add(bettingPanel, BorderLayout.WEST);

        //roundPanel.setBackground(Color.WHITE);
        roundPanel.setOpaque(false);
        roundPanel.setLayout(new FlowLayout());
        roundPanel.setPreferredSize(new Dimension(0,50));

        roundLabel.setBackground(new Color(104,187,227));
        roundLabel.setOpaque(true);
        roundLabel.setFont(new Font("Serif", Font.BOLD, 25));
        roundLabel.setForeground(Color.BLACK);
        roundLabel.setText("ROUND");
        roundPanel.add(roundLabel);

        topPanel.add(roundPanel, BorderLayout.NORTH);

        //imagePanel.setBackground(Color.GREEN);
        imagePanel.setOpaque(false);
        imagePanel.setLayout(new BorderLayout());


        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        imagePanel.add(imageLabel);

        topPanel.add(imagePanel, BorderLayout.CENTER);


        b250.setBackground(new Color(104,187,227));
        b500.setBackground(new Color(104,187,227));
        b750.setBackground(new Color(104,187,227));
        b1000.setBackground(new Color(104,187,227));
        b500.setForeground(Color.BLACK);
        b750.setForeground(Color.BLACK);
        b1000.setForeground(Color.BLACK);
        b250.setForeground(Color.BLACK);
        b250.setEnabled(false);
        b500.setEnabled(false);
        b750.setEnabled(false);
        b1000.setEnabled(false);
        b250.setVisible(false);
        b500.setVisible(false);
        b750.setVisible(false);
        b1000.setVisible(false);
        b250.addActionListener(this);
        b500.addActionListener(this);
        b750.addActionListener(this);
        b1000.addActionListener(this);
        bettingPanel.add(b250);
        bettingPanel.add(b500);
        bettingPanel.add(b750);
        bettingPanel.add(b1000);
    }

    /** Add components to middlePanel */
    private void middlePanel()
    {
        question.setBackground(new Color(104,187,227));
        question.setOpaque(true);
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setVerticalAlignment(SwingConstants.CENTER);
        question.setForeground(Color.BLACK);
        middlePanel.add(question);
    }

    /** Add components to bottomPanel */
    private void BottomPanel(int numberOfPlayers)
    {
        lPointsPanel.setBackground(new Color(104,187,227));
        lPointsPanel.setLayout(new BorderLayout());
        lPointsPanel.setPreferredSize(new Dimension(100,100));
        ((FlowLayout)bottomPanel.getLayout()).setHgap(100);

        lPointsLabel.setText("0");
        lPointsLabel.setHorizontalAlignment(JLabel.CENTER);
        lPointsLabel.setForeground(Color.BLACK);
        lPointsLabel.setBackground(new Color(104,187,227));
        lPointsPanel.add(lPointsLabel);

        bottomPanel.add(lPointsPanel);

        answersPanel.setBackground(new Color(69,43,217));
        answersPanel.setLayout(new GridLayout(2,2,5,5));
        answersPanel.setPreferredSize(new Dimension(800,200));
        bottomPanel.add(answersPanel, BorderLayout.CENTER);

        if (numberOfPlayers != 2) {
            rPointsPanel.setBackground(new Color(69,43,217));
            rPointsLabel.setText("");
        }else {
            rPointsPanel.setBackground(new Color(104, 187, 227));
            rPointsLabel.setText("0");
        }
        rPointsPanel.setLayout(new BorderLayout());
        rPointsPanel.setPreferredSize(new Dimension(100,100));
        ((FlowLayout)bottomPanel.getLayout()).setHgap(100);
        bottomPanel.add(rPointsPanel);

        rPointsLabel.setHorizontalAlignment(JLabel.CENTER);
        rPointsLabel.setForeground(Color.BLACK);
        rPointsLabel.setBackground(new Color(104,187,227));
        rPointsPanel.add(rPointsLabel);

        answerA.setForeground(Color.BLACK);
        answerB.setForeground(Color.BLACK);
        answerC.setForeground(Color.BLACK);
        answerD.setForeground(Color.BLACK);
        answerA.setBackground(new Color(104,187,227));
        answerB.setBackground(new Color(104,187,227));
        answerC.setBackground(new Color(104,187,227));
        answerD.setBackground(new Color(104,187,227));
        answerA.addActionListener(this);
        answerB.addActionListener(this);
        answerC.addActionListener(this);
        answerD.addActionListener(this);

        answersPanel.add(answerA);
        answersPanel.add(answerB);
        answersPanel.add(answerC);
        answersPanel.add(answerD);
    }

    /** When the round is betting it makes the buttons for bet visible or invisible depend on flag.
     * @param flag true : make the buttons vidible , false : make the buttons invisible */
    public void setActivityBB(boolean flag)
    {
        b250.setEnabled(flag);
        b500.setEnabled(flag);
        b750.setEnabled(flag);
        b1000.setEnabled(flag);
        b250.setVisible(flag);
        b500.setVisible(flag);
        b750.setVisible(flag);
        b1000.setVisible(flag);
    }

    /** Setter for the answers
     * @param answerA the answer A.
     * @param answerB the answer B.
     * @param answerC the answer C.
     * @param answerD the answer D. */
    public void setAnswers(String answerA, String answerB, String answerC, String answerD)
    {
        this.answerA.setText("A.  " + answerA);
        this.answerB.setText("B.  " + answerB);
        this.answerC.setText("C.  " + answerC);
        this.answerD.setText("D.  " + answerD);
    }

    /** Set the quesiton.
     * @param question the question */
    public void setQuestion(String question)
    {
        this.question.setText(question);
    }

    /** Set the category. (This happen in betting round while betting).
     * @param category the category */
    public void setCategory(String category) { question.setText(category); }

    /** Update the timer when the time is reducing.
     * @param timeLeft the remaining time. */
    public void setTimer( String timeLeft) { timer.setText(timeLeft);}

    /** Update the points of player1.
     * @param points the points */
    public void setPoints1(String points){
        lPointsLabel.setText(points);
    }

    /** Update the points of player2.
     * @param points the points */
    public void setPoints2(String points){
        rPointsLabel.setText(points);
    }

    /** Update the type of round.
     * @param round the type of round */
    public void setRoundText(String round){
        roundLabel.setText(round);
    }

    /** Given the key of right question it change the color to green and all the others colors to red.
     * @param theRightOne the key of the right question. */
    public void colorTheAnswers(String theRightOne) {
        switch (theRightOne) {
            case "A":
                answerA.setBackground(Color.GREEN);
                answerB.setBackground(Color.RED);
                answerC.setBackground(Color.RED);
                answerD.setBackground(Color.RED);
                break;
            case "B":
                answerB.setBackground(Color.GREEN);
                answerA.setBackground(Color.RED);
                answerC.setBackground(Color.RED);
                answerD.setBackground(Color.RED);
                break;
            case "C":
                answerC.setBackground(Color.GREEN);
                answerA.setBackground(Color.RED);
                answerB.setBackground(Color.RED);
                answerD.setBackground(Color.RED);
                break;
            default:
                answerD.setBackground(Color.GREEN);
                answerA.setBackground(Color.RED);
                answerB.setBackground(Color.RED);
                answerC.setBackground(Color.RED);
                break;
        }
        playFrame.setVisible(true);
    }

    /** Repaint the answers to original color. */
    public void repaintAnswers(){
        answerA.setBackground(new Color(104,187,227));
        answerB.setBackground(new Color(104,187,227));
        answerC.setBackground(new Color(104,187,227));
        answerD.setBackground(new Color(104,187,227));
    }

    /** This function sets the KeyListener and is responsible to control the buttons. (when buttons can be
     * pressed and what to do after. */
    private void setKeyWords() {
            playFrame.setFocusable(true);
            playFrame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);
                    try {
                        switch (e.getKeyChar()) {
                            case 'q':
                                // The user is unable to press a button if:
                                // 1) one player is playing and the category is betting and he didn't bet before (true) OR
                                // two players are playing and the category is betting and ( none of them have bet or one of the have.)
                                // 2) he answered again this question before.
                                if (!((game.getNumberOfPlayers() == 1 && game.getCategory() == 1 && cantPressThatWhileBetting1) || (game.getNumberOfPlayers() == 2 && game.getCategory() == 1 && ((cantPressThatWhileBetting1 && cantPressThatWhileBetting2) || cantPressThatWhileBetting1!=cantPressThatWhileBetting2)))) {
                                    if (!isPlayer1) {
                                        userAnswer[0] = "A"; //Save the answer of player.
                                        isPlayer1 = true; // The player answered.
                                        game.keyPressed(0);
                                    }
                                }
                                break;
                            case 'w':
                                if (!((game.getNumberOfPlayers() == 1 && game.getCategory() == 1 && cantPressThatWhileBetting1) || (game.getNumberOfPlayers() == 2 && game.getCategory() == 1 && ((cantPressThatWhileBetting1 && cantPressThatWhileBetting2) || cantPressThatWhileBetting1!=cantPressThatWhileBetting2)))) {
                                    if (!isPlayer1) {
                                        userAnswer[0] = "B"; //Save the answer of player.
                                        isPlayer1 = true; // The player answered.
                                        game.keyPressed(0);
                                    }
                                }
                                break;
                            case 'a':
                                if (!((game.getNumberOfPlayers() == 1 && game.getCategory() == 1 && cantPressThatWhileBetting1) || (game.getNumberOfPlayers() == 2 && game.getCategory() == 1 && ((cantPressThatWhileBetting1 && cantPressThatWhileBetting2) || cantPressThatWhileBetting1!=cantPressThatWhileBetting2)))) {
                                    if (!isPlayer1) {
                                        userAnswer[0] = "C"; //Save the answer of player.
                                        isPlayer1 = true; // The player answered.
                                        game.keyPressed(0);
                                    }
                                }
                                break;
                            case 's':
                                if (!((game.getNumberOfPlayers() == 1 && game.getCategory() == 1 && cantPressThatWhileBetting1) || (game.getNumberOfPlayers() == 2 && game.getCategory() == 1 && ((cantPressThatWhileBetting1 && cantPressThatWhileBetting2) || cantPressThatWhileBetting1!=cantPressThatWhileBetting2)))) {
                                    if (!isPlayer1) {
                                        userAnswer[0] = "D"; //Save the answer of player.
                                        isPlayer1 = true; // The player answered.
                                        game.keyPressed(0);
                                    }
                                }
                                break;
                            case '1':
                                // If the round is betting.
                                if (game.getCategory() == 1) {
                                    isBet1 = true; // The player has bet.
                                    cantPressThatWhileBetting1 = false; // The player now can answer to a question.
                                    game.setTheBet(250, 0, isBet1_2());
                                }
                                break;
                            case '2':
                                if (game.getCategory() == 1) {
                                    isBet1 = true; // The player has bet.
                                    cantPressThatWhileBetting1 = false; // The player now can answer to a question.
                                    game.setTheBet(500, 0, isBet1_2());
                                }
                                break;
                            case '3':
                                if (game.getCategory() == 1) {
                                    isBet1 = true; // The player has bet.
                                    cantPressThatWhileBetting1 = false; // The player now can answer to a question.
                                    game.setTheBet(750, 0, isBet1_2());
                                }
                                break;
                            case '4':
                                if (game.getCategory() == 1) {
                                    isBet1 = true; // The player has bet.
                                    cantPressThatWhileBetting1 = false; // The player now can answer to a question.
                                    game.setTheBet(1000, 0, isBet1_2());
                                }
                                break;
                            case 'i':
                                // If two players are playing those buttons might can be pressed.
                                // BUT they cant be pressed if:
                                // 1) The category is betting and ( none of them have bet or one of the have.)
                                // 2) He answered again this question before.
                                if (game.getNumberOfPlayers() == 2) {
                                    if (!(game.getCategory() == 1  && ((cantPressThatWhileBetting1 && cantPressThatWhileBetting2) || cantPressThatWhileBetting1!=cantPressThatWhileBetting2))) {
                                        if (!isPlayer2) {
                                            userAnswer[1] = "A"; //Save the answer of player.
                                            isPlayer2 = true; // The player answered.
                                            game.keyPressed(1);
                                        }
                                    }
                                }
                                break;
                            case 'o':
                                if (game.getNumberOfPlayers() == 2) {
                                    if (!(game.getCategory() == 1  && ((cantPressThatWhileBetting1 && cantPressThatWhileBetting2) || cantPressThatWhileBetting1!=cantPressThatWhileBetting2))) {
                                        if (!isPlayer2) {
                                            userAnswer[1] = "B"; //Save the answer of player.
                                            isPlayer2 = true; // The player answered.
                                            game.keyPressed(1);
                                        }
                                    }
                                }
                                break;
                            case 'k':
                                if (game.getNumberOfPlayers() == 2) {
                                    if (!(game.getCategory() == 1  && ((cantPressThatWhileBetting1 && cantPressThatWhileBetting2) || cantPressThatWhileBetting1!=cantPressThatWhileBetting2))) {
                                        if (!isPlayer2) {
                                            userAnswer[1] = "C"; //Save the answer of player.
                                            isPlayer2 = true; // The player answered.
                                            game.keyPressed(1);
                                        }
                                    }
                                }
                                break;
                            case 'l':
                                if (game.getNumberOfPlayers() == 2) {
                                    if (!(game.getCategory() == 1  && ((cantPressThatWhileBetting1 && cantPressThatWhileBetting2) || cantPressThatWhileBetting1!=cantPressThatWhileBetting2))) {
                                        if (!isPlayer2) {
                                            userAnswer[1] = "D"; //Save the answer of player.
                                            isPlayer2 = true; // The player answered.
                                            game.keyPressed(1);
                                        }
                                    }
                                }
                                break;
                            case '7':
                                if (game.getCategory() == 1) {
                                    if (game.getNumberOfPlayers() == 2) {
                                        isBet2 = true; // The player has bet.
                                        cantPressThatWhileBetting2 = false; // The player now can answer to a question.
                                        game.setTheBet(250, 1, isBet1_2());
                                    }
                                }
                                break;
                            case '8':
                                if (game.getCategory() == 1) {
                                    if (game.getNumberOfPlayers() == 2) {
                                        isBet2 = true; // The player has bet.
                                        cantPressThatWhileBetting2 = false;
                                        game.setTheBet(500, 1, isBet1_2());
                                    }
                                }
                                break;
                            case '9':
                                if (game.getCategory() == 1) {
                                    if (game.getNumberOfPlayers() == 2) {
                                        isBet2 = true; // The player has bet.
                                        cantPressThatWhileBetting2 = false; // The player now can answer to a question.
                                        game.setTheBet(750, 1, isBet1_2());
                                    }
                                }
                                break;
                            case '0':
                                if (game.getCategory() == 1) {
                                    if (game.getNumberOfPlayers() == 2) {
                                        isBet2 = true; // The player has bet.
                                        cantPressThatWhileBetting2 = false; // The player now can answer to a question.
                                        game.setTheBet(1000, 1, isBet1_2());
                                    }
                                }
                                    break;
                        }
                        // If 1 player is playing and he answered the question.
                        if (game.getNumberOfPlayers() == 1) {
                            if (isPlayer1) {
                                isPlayer1 = false; // Initialise the variable to didn't answered .
                                isBet1 = false; // Initialise the variable to didn't bet .
                                playFrame.setFocusable(false); // Disable the frame so none can press anything.
                                // Pause the program for 1 second.
                                ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
                                schedule.schedule(new NextQuestion(game), 1, TimeUnit.SECONDS);
                                schedule.shutdown();
                            }
                            // If 2 players are playing and both answered.
                        } else if (game.getNumberOfPlayers() == 2) {
                            if (isPlayer1 && isPlayer2) {
                                isPlayer1 = false; // Initialise the variable to didn't answered .
                                isPlayer2 = false; // Initialise the variable to didn't answered .
                                playFrame.setFocusable(false); // Disable the frame so none can press anything.
                                // Pause the program for 1 second.
                                ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
                                schedule.schedule(new NextQuestion(game), 1, TimeUnit.SECONDS);
                                schedule.shutdown();

                            }
                        }
                        // If both players answered.
                        if (isBet1_2()) {
                            isBet1 = false; // Initialise the variable to didn't bet .
                            isBet2 = false; // Initialise the variable to didn't bet .
                        }
                    } catch(FileNotFoundException | InterruptedException fileNotFoundException){
                        fileNotFoundException.printStackTrace();
                    }

                }
            });
    }

    /** This function sets the image to null if the parameter is false otherwise it sets the image.
     * @param imageName the image */
    public void setImage(String imageName){
        if (imageName.equals("false")) {
            imageLabel.setIcon(null);
        }
        else{
            if (Resource.getURL(imageName) != null)
                imageLabel.setIcon(new ImageIcon(Resource.getURL(imageName)));
        }
    }

    /** Initialise the variables for next question */
    public void setCantPressThatWhileBetting(){
        cantPressThatWhileBetting1 = true;
        cantPressThatWhileBetting2 = true;
    }

    /** @return returns true if both players have answered, otherwise false */
    public boolean isPlayer1_2(){
        return isPlayer1 && isPlayer2;
    }

    /** @return returns true if both players have bet, otherwise false */
    public boolean isBet1_2(){
        return isBet1 && isBet2;
    }

    /** Give the focus to frame */
    public void setFocusToFrame(){
        playFrame.setFocusable(true);
        playFrame.requestFocus();
    }

    /** Disable the keyListener. */
    public void disableKeyListener(){
        playFrame.removeKeyListener(playFrame.getKeyListeners()[0]);
    }

    /** Go to next question. */
    public void nextQuestion() throws FileNotFoundException { game.nextQuestion(); }

    /** Get the user answer, depend which player.
     * @param thisPlayer 0 is the first player, 1 is the second player.
     * @return returns the answer of a player. */
    public String getUserAnswer(int thisPlayer){ return userAnswer[thisPlayer]; }


    /** In case a player uses the mouse and click any button, the listener restore the focus to the frame. */
    @Override
    public void actionPerformed(ActionEvent e) {
        playFrame.requestFocus();
    }

}


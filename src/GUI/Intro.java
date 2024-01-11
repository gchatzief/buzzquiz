package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import GUI.Images.Resource;
import MainClasses.UpdateScores;

/** This class is the first class that will be executed and presents the intro of the game. */
public class Intro extends JFrame  {

    private final JFrame introFrame;

    private final JPanel logoPanel;
    private final JPanel infoPanel;
    private final JPanel scoresPanel;
    private final JLabel logoLabel;
    private final JButton infoButton;
    private final JButton scoresButton;

    private final JPanel basePlayPanel;
    private final JPanel playPanel;
    private final JButton playButton;

    /** The constructor initialize the GUI components. */
    public Intro()
    {
        logoPanel = new JPanel();
        infoPanel = new JPanel();
        scoresPanel = new JPanel();
        logoPanel();
        logoLabel = new JLabel();
        logoLabel();
        infoButton = new JButton();
        infoButton();
        scoresButton = new JButton();
        scoresButton();

        basePlayPanel = new JPanel();
        basePlayPanel();
        playPanel = new JPanel();
        playPanel();
        playButton = new JButton();
        playButton();

        introFrame = new JFrame();
        introFrame();
    }

    /** Sets the main panels. */
    private void logoPanel(){
        //logoPanel.setBackground(Color.GREEN);
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new BorderLayout());
        logoPanel.setPreferredSize(new Dimension(0,300));

        //infoPanel.setBackground(Color.YELLOW);
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setPreferredSize(new Dimension(62,0));

        //scoresPanel.setBackground(Color.YELLOW);
        scoresPanel.setOpaque(false);
        scoresPanel.setLayout(new BorderLayout());
        scoresPanel.setPreferredSize(new Dimension(62,0));

        logoPanel.add(infoPanel, BorderLayout.WEST);
        logoPanel.add(scoresPanel, BorderLayout.EAST);
    }

    /** Sets the logo of the game. */
    private void logoLabel(){
        if (Resource.getURL("Quizconq.png") != null)
            logoLabel.setIcon(new ImageIcon(Resource.getURL("Quizconq.png")));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoPanel.add(logoLabel);
    }

    /** Sets the button for the game info. */
    private void infoButton(){
        if (Resource.getURL("questionmark.png") != null)
            infoButton.setIcon(new ImageIcon(Resource.getURL("questionmark.png")));
        infoButton.setBackground(new Color(69,43,217));
        infoButton.setBorder(BorderFactory.createLineBorder(new Color(69,43,217)));
        infoButton.addActionListener(e -> {
            if (e.getSource() == infoButton){
                new Infos();
            }
        });
        infoPanel.add(infoButton, BorderLayout.NORTH);
    }

    /** Sets the button for the scores. */
    private void scoresButton(){
        if (Resource.getURL("Scores.png") != null)
            scoresButton.setIcon(new ImageIcon(Resource.getURL("Scores.png")));
        scoresButton.setBackground(new Color(69,43,217));
        scoresButton.setBorder(BorderFactory.createLineBorder(new Color(69,43,217)));
        scoresButton.addActionListener(e -> {
            if (e.getSource() == scoresButton){
                try {
                    UpdateScores newScores = new UpdateScores();
                    new HighScores(newScores.getSingleUpdate(), newScores.getMultiUpdate());
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        scoresPanel.add(scoresButton, BorderLayout.NORTH);
    }

    /** Sets the base panel for the play button. */
    private void basePlayPanel(){
        //basePlayPanel.setBackground(Color.BLACK);
        basePlayPanel.setOpaque(false);
        basePlayPanel.setLayout(new FlowLayout());
        basePlayPanel.setPreferredSize(new Dimension(0,330));
    }

    /** Sets the panel that play button will be added. */
    private void playPanel(){
        //playPanel.setBackground(new Color(69,43,217));
        playPanel.setOpaque(false);
        playPanel.setLayout(new BorderLayout());
        playPanel.setPreferredSize(new Dimension(120,120));
        basePlayPanel.add(playPanel);
    }

    /** Sets the play button which have an action listener that will change the frame to menu frame. */
    private void playButton(){
        if (Resource.getURL("Play.png") != null)
            playButton.setIcon(new ImageIcon(Resource.getURL("Play.png")));
        playButton.setBackground(new Color(69,43,217));
        playButton.setBorder(BorderFactory.createLineBorder(new Color(69,43,217)));
        playButton.addActionListener(e -> {
            if (e.getSource()==playButton) {
                try {
                    new Menu(introFrame);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        playPanel.add(playButton);


    }

    /** Sets the introFrame. */
    private void introFrame()
    {
        introFrame.setSize(1350,725);
        introFrame.setTitle("Quizqonq");
        introFrame.setResizable(true);
        introFrame.getContentPane().setBackground(new Color(69, 43, 217));
        introFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introFrame.add(logoPanel, BorderLayout.NORTH);
        introFrame.add(basePlayPanel, BorderLayout.SOUTH);

        introFrame.setVisible(true);
    }
}


package GUI;

import javax.swing.*;
import java.awt.*;

/** This class creates a new windows that shows the scores of the players. */
public class HighScores extends JFrame{

    private final JPanel titlesPanel;
    private final JPanel titlesPanelHalfRight;
    private final JLabel singleLabel;
    private final JLabel multiLabel;
    private final JPanel singlePanel;
    private final JTextArea singleScores;
    private JScrollPane singleScroller;
    private final JPanel multiPanel;
    private final JTextArea multiScores;
    private JScrollPane multiScroller;
    private final JFrame scoresFrame;

    /**The constructor initialize the GUI components.
     * @param scoresOfSingle it's a string that contains the high scores of all players in order with their names.
     * @param scoresOfMulti it's a string that contains the wins of all players in order with their names. */
    public HighScores(String scoresOfSingle, String scoresOfMulti){

        titlesPanel = new JPanel();
        titlesPanelHalfRight = new JPanel();
        singleLabel = new JLabel();
        multiLabel = new JLabel();
        titlesPanel();
        singlePanel = new JPanel();
        singleScores = new JTextArea(scoresOfSingle);
        singleScroller = new JScrollPane();
        singlePanel();
        multiPanel = new JPanel();
        multiScores = new JTextArea(scoresOfMulti);
        multiScroller = new JScrollPane();
        multiPanel();

        scoresFrame = new JFrame();
        scoresFrame();

    }

    /** Sets the titlesPanel which contains labels of single and multi players. */
    private void titlesPanel(){
        //titlesPanel.setBackground(Color.RED);
        titlesPanel.setOpaque(false);
        titlesPanel.setLayout(new BorderLayout());
        titlesPanel.setPreferredSize(new Dimension(0,100));

        //titlesPanelHalfRight.setBackground(Color.YELLOW);
        titlesPanelHalfRight.setOpaque(false);
        titlesPanelHalfRight.setLayout(new BorderLayout());
        titlesPanelHalfRight.setPreferredSize(new Dimension(500,0));

        titlesPanel.add(titlesPanelHalfRight, BorderLayout.EAST);

        singleLabel.setText("Single Players High Scores");
        singleLabel.setBackground(new Color(104,187,227));
        singleLabel.setFont(new Font("Serif", Font.BOLD, 16));
        singleLabel.setForeground(Color.BLACK);
        singleLabel.setVerticalAlignment(JLabel.BOTTOM);
        singleLabel.setHorizontalAlignment(JLabel.CENTER);

        titlesPanel.add(singleLabel);

        multiLabel.setText("Multi Players High Wins");
        multiLabel.setBackground(new Color(104,187,227));
        multiLabel.setFont(new Font("Serif", Font.BOLD, 17));
        multiLabel.setForeground(Color.BLACK);
        multiLabel.setVerticalAlignment(JLabel.BOTTOM);
        multiLabel.setHorizontalAlignment(JLabel.CENTER);

        titlesPanelHalfRight.add(multiLabel);
    }

    /** Sets the singlePanel which contains the high scores. */
    private void singlePanel(){

        //singlePanel.setBackground(Color.YELLOW);
        singlePanel.setOpaque(false);
        singlePanel.setLayout(new FlowLayout());
        singlePanel.setPreferredSize(new Dimension(500,0));

        singleScores.setPreferredSize(new Dimension(200,5000));
        singleScores.setLineWrap(true);
        singleScores.setWrapStyleWord(true);
        singleScores.setBackground(new Color(104,187,227));
        singleScores.setEditable(false);

        singleScroller = new JScrollPane(singleScores);
        singleScroller.setPreferredSize(new Dimension(200,200));
        singleScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        singlePanel.add(singleScroller);

    }

    /** Sets the multiPanel which contains the wins. */
    private void multiPanel(){
        //multiPanel.setBackground(Color.GREEN);
        multiPanel.setOpaque(false);
        multiPanel.setLayout(new FlowLayout());
        multiPanel.setPreferredSize(new Dimension(500,0));

        multiScores.setPreferredSize(new Dimension(200,5000));
        multiScores.setLineWrap(true);
        multiScores.setWrapStyleWord(true);
        multiScores.setBackground(new Color(104,187,227));
        multiScores.setEditable(false);

        multiScroller = new JScrollPane(multiScores);
        multiScroller.setPreferredSize(new Dimension(200,200));
        multiScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        multiPanel.add(multiScroller);
        }
    /** Sets the main frame scoresFrame which contains it all. */
    private void scoresFrame(){

        scoresFrame.setSize(1000,500);
        scoresFrame.setTitle("Scores");
        scoresFrame.setResizable(false);
        scoresFrame.getContentPane().setBackground(new Color(69, 43, 217));
        scoresFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);

        scoresFrame.add(singlePanel, BorderLayout.WEST);
        scoresFrame.add(multiPanel, BorderLayout.EAST);
        scoresFrame.add(titlesPanel, BorderLayout.NORTH);
        scoresFrame.setVisible(true);
    }

}

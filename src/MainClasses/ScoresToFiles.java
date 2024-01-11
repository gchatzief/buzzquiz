package MainClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;

/** This class save to the file the single or multi players games.*/
public class ScoresToFiles {

    private final SingleMultiPlayer players;
    private final DecimalFormat decimalFormat;
    private final int theWinner; // Who won.
    private final boolean tie; // If there is a tie is true.

    /** Constructor initialise the variables and create the files if they don't exists.
     * @param players an instance of the class to get the points and names of the players.
     * @param theWinner who won the game.
     * @param tie if there is a tie is true. */
    public ScoresToFiles(SingleMultiPlayer players, int theWinner, boolean tie){

        this.players = players;
        this.theWinner = theWinner;
        this.tie = tie;
        decimalFormat = new DecimalFormat("#.#");

        try {
            File singlePlayerScores = new File("singlePlayerScores.doc");
            File multiPlayersScores = new File("multiPlayersScores.doc");
            if (singlePlayerScores.createNewFile()) { System.out.println("Single player file has created.");}
            if (multiPlayersScores.createNewFile()) { System.out.println("Multi player file has created.");}
        }catch(IOException e){
            System.out.println("An error occurred.");
        }
        saveTheScores();
    }

    /** This function save the results depend if 1 player is playing or 2. */
    private void saveTheScores(){

        try {
            if (players.getNumberOfPlayers() == 1) {
                FileWriter myWriter = new FileWriter("singlePlayerScores.doc", true);
                myWriter.write(LocalDate.now() + " :  \"" + players.getPerson(0).getName() + "\": \""+ decimalFormat.format(players.getPerson(0).getPoints()) + "\" points.\n\n");
                myWriter.close();
            }
            else {
                FileWriter myWriter = new FileWriter("multiPlayersScores.doc", true);
                if (!tie) {
                    myWriter.write(LocalDate.now() + " :  " + players.getPerson(0).getName() + " - " + players.getPerson(1).getName()
                    + "  " + decimalFormat.format(players.getPerson(0).getPoints()) + " || " + decimalFormat.format(players.getPerson(1).getPoints()) + " points."
                    + " The winner is \"" + players.getPerson(theWinner).getName() + "\"\n\n");
                }else {
                    myWriter.write(LocalDate.now() + " :  " + players.getPerson(0).getName() + " - " + players.getPerson(1).getName()
                    + "  " + decimalFormat.format(players.getPerson(0).getPoints()) + " || " + decimalFormat.format(players.getPerson(1).getPoints()) + " points."
                    + " There is no winner \"" + "TIE" + "\"\n\n");
                }
                myWriter.close();
            }
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}

package MainClasses;

import GUI.Play;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/** This class asks how many questions and how many rounds the player will play and welcomes him to the game*/
public class Game {

    private final Questions question;
    private final RoundCategories roundCategories;
    private final SingleMultiPlayer players;
    private final Play play;

    int random_category; // The number of category.
    private int number_of_rounds; //The number of rounds.
    private int round_questions; //The number of questions.
    private int numberOfPlayers; //The number of players.
    private final DecimalFormat decimalFormat = new DecimalFormat("#.#"); //Format the values to 1 decimal digit.

    private int questionCounter; // Count the questions in each round.
    private int questionSumm; // Count the total questions in game.
    private int counter; //
    private final String[] rounds = {"Right Answer","Betting","Stop The Timer","Fast Answer", "Thermometer"}; // The types of rounds.

    /**Μake a constructor
     * @param play the play class.
     * @param number_of_rounds the number of rounds.
     * @param round_questions the questions of each round.
     * @param numberOfPlayers the number of players.
     * @param playersName the names of the players. */
    public Game(Play play, int number_of_rounds, int round_questions, int numberOfPlayers, String[] playersName) throws FileNotFoundException {

        this.play = play;

        // Initialise the values to zero.
        random_category = 0;
        questionCounter = 0;
        questionSumm = 0;

        players = new SingleMultiPlayer();
        question = new Questions(play, players);
        roundCategories = new RoundCategories(players, question, numberOfPlayers ,play);

        counter = 1; // The use of counter it's for display the current round.
        setGame(number_of_rounds, round_questions, numberOfPlayers, playersName);

        random_category = roundCategories.chooseRandomCategory();
        play.setRoundText(rounds[random_category] + "  " + (questionCounter+1) + "/" + round_questions + " : " + counter + "/" + (number_of_rounds));
        roundCategories.setRoundCategory();

    }

    /** This function prepare the game for the next question. */
    public void nextQuestion () throws FileNotFoundException {
        // If the round is thermometer set the counter of question equal the total question in each round so it wil change round after that
        // and add the those question to total questions.
        // (RoundCategories class will call this only if the thermometer round is finished.)
        // otherwise increase them by one to go to next question in any other type of round.
        if (random_category == 4){
            questionCounter = round_questions;
            questionSumm += round_questions;
        }else {
            questionSumm++;
            questionCounter++;
        }
        // If the total questions didn't reach all the end.
        if (questionSumm < round_questions * number_of_rounds) {
            // If the counter reach the total question in each round change the type of round and update the the info of rounds.
            if (questionCounter == round_questions) {
                random_category = roundCategories.chooseRandomCategory();
                counter++; // Increase the round counter by one since the round ends.
                questionCounter = 0; // reset the counter.
            }
            // Update the round info.
            play.setRoundText(rounds[random_category] + "  " + (questionCounter+1) + "/" + (round_questions) + " : " + counter + "/" + (number_of_rounds));
            roundCategories.setRoundCategory(); // Set the game to play the new round.
            // If total question reach to the end.
        }else if (questionSumm == round_questions * number_of_rounds){
            // If 1 player is playing announce the info and save the score.
            if (players.getNumberOfPlayers() == 1) {
                play.setRoundText(" Congrats! Your Total Score is " + decimalFormat.format(players.getPerson(0).getPoints()) + " ");
                play.setPoints1("");
                play.setQuestion("");
                play.setAnswers("","","","");
                play.setImage("false");
                play.disableKeyListener();
                new ScoresToFiles(players, 0, true);
            }
            else {
                // If 2 players are playing then announce the winner depend on the scores and save the wins.
                play.setPoints1(String.valueOf(decimalFormat.format(players.getPerson(0).getPoints())));
                play.setPoints2(String.valueOf(decimalFormat.format(players.getPerson(1).getPoints())));
                if (players.getPerson(0).getPoints() > players.getPerson(1).getPoints()) {
                    play.setRoundText(" The WINNER is " + players.getPerson(0).getName() + " ");
                    new ScoresToFiles(players, 0, false);
                }
                else if (players.getPerson(0).getPoints() < players.getPerson(1).getPoints()) {
                    play.setRoundText(" The WINNER is " + players.getPerson(1).getName() + " ");
                    new ScoresToFiles(players, 1, false );
                }
                else {
                    play.setRoundText(" TIE ");
                    new ScoresToFiles(players, 1, true);
                }
                play.setQuestion("");
                play.setAnswers("","","","");
                play.setImage("false");
                play.disableKeyListener();
            }
        }else {
            System.out.println(" Sorry, Out of Questions...");
            System.exit(0);
        }
    }

    /** This function is being called when a key pressed in every question to check if it's the correct answer  or not.
     * @param thisPlayer 0 for player1, 1 for player2. */
    public void keyPressed(int thisPlayer) throws FileNotFoundException, InterruptedException {
        // If 1 player is playing or 2 player are playing and both answered to the question, color them.
        if (players.getNumberOfPlayers() == 1 || (players.getNumberOfPlayers() == 2 && play.isPlayer1_2()))
            play.colorTheAnswers(question.getRightAnswer());
        roundCategories.randomCategory(random_category, thisPlayer); // Check the user answer.
        // If 1 player is playing update the score immediately.
        if (players.getNumberOfPlayers() == 1){
            play.setPoints1(decimalFormat.format(players.getPerson(0).getPoints()));
        }
        // Otherwise if 2 players are playing and they both answered and round isn't thermometer then update the scores.
        else if ((players.getNumberOfPlayers()==2 && play.isPlayer1_2())){
            if (random_category != 4) {
                play.setPoints1(decimalFormat.format(players.getPerson(0).getPoints()));
                play.setPoints2(decimalFormat.format(players.getPerson(1).getPoints()));
            }
        }

    }

    /** (This function is used as a bridge) Sets the bets  and the question for Betting round because in the first set players had to bet.
     * @param bet the bet of player.
     * @param thisPlayer which player bets
     * @param flag it's true if they both player bet. */
    public void setTheBet(int bet, int thisPlayer, boolean flag) {roundCategories.setBettingRound(bet, thisPlayer, flag);}

    /** (This function is used as a bridge) Its a NextQuestion function for thermometer round so it can change questions without change the flow of the program. */
    public void setTheThermometer() throws FileNotFoundException {roundCategories.setThermometerRound(); }

    /** (This function is used as a bridge)  Repaint the answers to original color. */
    public void repaintAnswers(){
        play.repaintAnswers();
    }

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public int getCategory() { return random_category; }

    public void setFocusToFrame(){
        play.setFocusToFrame();
    }

    /** Set the number of question, rounds, and players which user choose.
     * @param number_of_rounds the number of rounds.
     * @param round_questions the questions of each round.
     * @param numberOfPlayers the number of players.
     * @param playersName the names of the players. */
    private void setGame(int number_of_rounds, int round_questions, int numberOfPlayers, String[] playersName)
    {
        this.number_of_rounds = number_of_rounds;
        this.round_questions = round_questions;
        this.numberOfPlayers = numberOfPlayers;
        for (int i=0; i<numberOfPlayers; i++)
            players.createName(playersName[i]);
    }

}

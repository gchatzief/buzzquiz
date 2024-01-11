package MainClasses;

import GUI.Play;
import TypesOfRounds.*;


import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Random;

/** This Class is responsible for managing the different types of categories and the flow of the game every round.*/
public class RoundCategories {

    private final int numberOfCategories;
    private final Questions question;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.#"); //Format the values to 1 decimal digit.


    private final Play play;
    private final SingleMultiPlayer players;
    private StopTheTimer stopthetimer;
    private Thermometer thermometer;

    private final int[] bets = {0,0};
    private int category;
    boolean fastAnswer;

    /**Initialize the variables.*/
    public RoundCategories(SingleMultiPlayer players, Questions question,int numberOfPlayers ,Play play) throws FileNotFoundException {

        // If one player is playing the rounds are 3. If 2 players are playing the rounds are 5.
        if (numberOfPlayers == 1) {
            numberOfCategories = 3;
        }
        else {
            numberOfCategories = 5;
        }

        this.question = question;
        this.play = play;
        this.players = players;

        fastAnswer = false; // This variable is to define the faster player in fast answer round.

    }

    /** This function is being called every time a round ends.
     * @return random integer which represent who type of round the user will play. */
    public int chooseRandomCategory()
    {
        Random rand = new Random(); //Random int generator function.
        category = rand.nextInt(numberOfCategories); //Gets the random integer (Between 0 and numberOfCategories-1)
        return category;
    }

    /** This function is used to sett the round every time a new round starts. */
    public void setRoundCategory() {
        if (question.findRandomQuestion()) { // If any question left , it will find a random  question that user will play in this round.
            switch (category) { //Depent the random number.
                case 0: //First case is right answer type.
                    play.setAnswers(question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD());
                    play.setQuestion(question.getQuestion());
                    play.setImage(question.getImageName());
                    break;
                case 1: //Second case is Betting type.
                    play.setAnswers("", "", "", "");
                    play.setQuestion("");
                    play.setImage("false");
                    play.setCategory(question.getCategory());
                    play.setActivityBB(true);
                    play.setCantPressThatWhileBetting();
                    break;
                case 2: //Third case is StopTheTimer type.
                    play.setAnswers(question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD());
                    play.setQuestion(question.getQuestion());
                    play.setImage(question.getImageName());
                    stopthetimer = new StopTheTimer(play);
                    break;
                case 3: //Forth case is FastAnswer type.
                    play.setAnswers(question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD());
                    play.setQuestion(question.getQuestion());
                    play.setImage(question.getImageName());
                    fastAnswer = false;
                    break;
                case 4: //Fifth case is Thermometer type.
                    play.setAnswers(question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD());
                    play.setQuestion(question.getQuestion());
                    play.setImage(question.getImageName());
                    play.setPoints1("0");
                    play.setPoints2("0");
                    thermometer = new Thermometer();
                    break;
            }
        }else { //If the are no more question, exit program.
            System.out.println("Out of questions");
            System.exit(0);
        }
    }

    /** This function is being called after the player bet.
     * @param bet the number of points the player bet.
     * @param thisPlayer which player bet.
     * @param flag if both players bet. */
    public void setBettingRound(int bet, int thisPlayer, boolean flag)
    {
        if (players.getNumberOfPlayers() == 1 || (players.getNumberOfPlayers()==2 && flag)) {
            play.setAnswers(question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD());
            play.setQuestion(question.getQuestion());
            play.setImage(question.getImageName());
        }
        bets[thisPlayer] = bet;
    }

    /** This function is a separate function for change the questions. When the thermometer ends it calls the
     * original next question function in Game. */
    public void setThermometerRound() throws FileNotFoundException {
        if (question.findRandomQuestion()){
            if (thermometer.end()){
                play.setPoints1(String.valueOf(decimalFormat.format(players.getPerson(0).getPoints())));
                play.setPoints2(String.valueOf(decimalFormat.format(players.getPerson(1).getPoints())));
                play.nextQuestion();
            }else {
                play.setAnswers(question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD());
                play.setQuestion(question.getQuestion());
                play.setImage(question.getImageName());
            }
        }
    }

    /** Depend the value of category parameter, a piece of code will be execute. This code is the type of round which the user will play for some questions.
     For each question the SinglePlayer object (player) will be informed depend the user answers.
     @param thisPlayer who player answered.
     @param category , with the value of this variable this method decides what kind of round the player will play.*/
    public void randomCategory(int category, int thisPlayer) {

        if (category == 0) { //Calls Correct_Answer type.
            RightAnswer rightAnswer= new RightAnswer();
            if (question.isCorrectAnswer(play.getUserAnswer(thisPlayer))) // It checks if the user answer is the correct answer.
                rightAnswer.correctAnswer(players, thisPlayer); // Add the points of this player.

        }else if (category == 1) { //Calls Betting type.
            Betting betting = new Betting(bets[thisPlayer]);
            if (question.isCorrectAnswer(play.getUserAnswer(thisPlayer))) {// It checks if the user answer is the correct answer.
                betting.correctAnswer(players, thisPlayer); // Add the points.
            } else {
                betting.wrongAnswer(players, thisPlayer); //Reduce the points.
            }
            // In the end of question set the buttons off if 1 player is playing or 2 and they both answered.
            if (players.getNumberOfPlayers() == 1 || (players.getNumberOfPlayers()==2 && play.isPlayer1_2()))
                play.setActivityBB(false);
        }
        else if (category == 2) { //Third case is StopTheTimer type.
            if (players.getNumberOfPlayers() == 1 || (players.getNumberOfPlayers()==2 && play.isPlayer1_2())) {
                stopthetimer.stopTimer();
                play.setTimer("");
            }
            if (question.isCorrectAnswer(play.getUserAnswer(thisPlayer))) {
                    stopthetimer.correctAnswer(players, thisPlayer);
            }
        }else if (category == 3){ //Forth case is FastAnswer type.
            FastAnswer fastanswer = new FastAnswer();
            if (!play.isPlayer1_2()) { // If one player answered.
                if (question.isCorrectAnswer(play.getUserAnswer(thisPlayer))){
                    fastanswer.correctAnswer(players, thisPlayer, true); //this player was the faster (true)
                    fastAnswer = true; // One player have already answered right first.
                }
            }else{
                // then if the the second answered correctly and the previews one didn't , then he is the faster, otherwise not.
                if (question.isCorrectAnswer(play.getUserAnswer(thisPlayer))){
                    fastanswer.correctAnswer(players, thisPlayer, !fastAnswer);
                }
            }
        }else if (category == 4){ //Fifth case is Thermometer type.
            if (question.isCorrectAnswer(play.getUserAnswer(thisPlayer))){
                thermometer.correctAnswer(players, thisPlayer);
            }
            if (play.isPlayer1_2()) {
                play.setPoints1(String.valueOf(thermometer.getFirstPlayerCount()));
                play.setPoints2(String.valueOf(thermometer.getSecondPlayerCount()));
            }
        }
        if (players.getNumberOfPlayers() == 1 || (players.getNumberOfPlayers()==2 && play.isPlayer1_2()))
            question.findRandomQuestion();
    }
}

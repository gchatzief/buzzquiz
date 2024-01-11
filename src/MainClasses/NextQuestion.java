package MainClasses;

import java.io.FileNotFoundException;

/** This class is created only so we can pause the program for some time and after go to next question.*/
public class NextQuestion implements Runnable{

    private final Game game;

    /** Constructor received an instance of the game. */
    public NextQuestion(Game game){
        this.game=game;
    }

    /** After 1 second past if the round is thermometer then call the "next question" for thermometer
        otherwise call the original next question function. */
    @Override
    public void run() {
        try {
            game.repaintAnswers();
            if (game.getCategory() == 4) {
                game.setTheThermometer();
            }
            else {
                game.nextQuestion();
            }
            game.setFocusToFrame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package TypesOfRounds;

import GUI.Play;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

/** This class represents a type of a round called "StopTheTimer". In this round the players have 10 seconds
 * to answer the question. If they answer correct they will get the number of remaining milliseconds.*/
public class StopTheTimer extends RightAnswer {

    private final Play play;

    private static final int MLS = 10000; // The total time (10 seconds)
    private int timeLeft; // The remaining time.

    Timer timer = new Timer();
    TimerTask countDown = new TimerTask() {
        @Override
        public void run() {
            timeLeft-=1;
            // Because we change the seconds from 5 to 10 we change multiply by 0.2 to divide by 2.0
            setPoints(timeLeft / 2.0);
            play.setTimer(String.valueOf(timeLeft/1000));

            if (timeLeft==0) {
                play.setTimer("");
                try {
                    play.nextQuestion(); // If time ends go to next question
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                countDown.cancel();
                timer.cancel();
            }

        }
    };

    /** The constructor initialise the variables.
     * @param play an object o the class Play. */
    public StopTheTimer(Play play) {

        super(MLS * 0.2);
        this.play=play;
        timeLeft = MLS;
        startTimer();

    }

    /** Start the timer. */
    private void startTimer()
    {
        timer.scheduleAtFixedRate(countDown,0,1);

    }

    /** Stop the timer. */
    public void stopTimer()
    {
        countDown.cancel();
        timer.cancel();
    }

}

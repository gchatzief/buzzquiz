package TypesOfRounds;

import MainClasses.SingleMultiPlayer;

/** This class represents a type of a round called "FastAnswer". The player who answered correct first
 gets 1000 points and the second gets 500.*/
public class FastAnswer{

    private final int points1;
    private final int points2;

    /** The constructor initialise the variables. */
    public FastAnswer()
    {
        points1=1000;
        points2=500;
    }

    /** If the player answered correct add the points depend who faster.
     * @param players an object of SingleMulti player that represents a player.
     * @param thisPlayer , who player answered.
     * @param first if this player is the faster. */
    public void correctAnswer(SingleMultiPlayer players, int thisPlayer, boolean first){
        if (first)
            players.getPerson(thisPlayer).setPoints(points1);
        else
            players.getPerson(thisPlayer).setPoints(points2);
    }
}

package TypesOfRounds;

import MainClasses.SingleMultiPlayer;

/** This class represents a type of a round called "Betting". The player will bet a number of points. If he find the correct
 answer he will earn the points, otherwise he will lose them.*/
public class Betting extends RightAnswer{

    private final int bet;

    /** The constructor initialize the variable bet with the value that the user choose. */
    public Betting(int bet)  {

        super(bet);
        this.bet= bet;
    }

    /** @param player , an object of SingleMulti player that represents a player.
     * @param thisPlayer , who player answered. */
    public void wrongAnswer(SingleMultiPlayer player, int thisPlayer){
        player.getPerson(thisPlayer).setPoints( (-1)*bet );
    }

}


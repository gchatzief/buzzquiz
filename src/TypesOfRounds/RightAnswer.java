package TypesOfRounds;

import MainClasses.SingleMultiPlayer;

/** This class gives 1000 points each time the player answers correctly*/
public class RightAnswer {

    private double points;

    /**Enters the value 1000 in the variable*/
    public RightAnswer()
    {
        points=1000;
    }

    /**Make a constructor*/
    public RightAnswer(double points) { this.points = points; }

    /** Set the points.
     * @param points the points. */
    protected void setPoints(double points)
    {
        this.points=points;
    }

    /** Adds the points to players.
     * @param player an object of SingleMulti player that represents a player.
     * @param thisPlayer who player answered. */
    public void correctAnswer(SingleMultiPlayer player, int thisPlayer){
        player.getPerson(thisPlayer).setPoints(points);
    }

}
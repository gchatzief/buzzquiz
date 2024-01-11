package MainClasses;

/** This class represents a player with his name and his points.*/
public class Person {

    private final String name;
    private double points;

    /** The constructor setts the name of the player and initialise his points to 0.
     * @param name the name of the player. */
    public Person(String name)
    {
        this.name=name;
        points=0;
    }

    /**@return name return the name*/
    public String getName() {
        return name;
    }

    /**Calculate the points.
     * @param points the points it need to add. */
    public void setPoints(double points){
        this.points+=points;
    }

    /**@return points return the points*/
    public double getPoints(){
        return points;
    }
}
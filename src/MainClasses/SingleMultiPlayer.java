package MainClasses;

import java.util.ArrayList;

/** This class creates an Array which contains the number of the players.*/
public class SingleMultiPlayer {

    private final ArrayList<Person> players; //The array list with the players.

    /** The constructor initialize the Arraylist. */
    public SingleMultiPlayer()
    {
        players= new ArrayList<>();
    }

    /** Create a Person with a name and adds it into Arraylist.
     * @param name the name. */
    public void createName(String name)
    {
        players.add(new Person(name));
    }

    /**@return returns an object of the Person. */
    public Person getPerson(int i)
    {
        return players.get(i);
    }

    /** @return the number of players that playing the game. */
    public int getNumberOfPlayers(){
        return players.size();
    }

}

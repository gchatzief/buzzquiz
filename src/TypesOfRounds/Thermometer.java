package TypesOfRounds;

import MainClasses.SingleMultiPlayer;

/** This class represents a type of a round called "Thermometer". In this round the first player that answer
 * correct 5 question gets 1000 points.*/
public class Thermometer{

    int firstPlayerCount;
    int secondPlayerCount;
    int points;
    boolean faster;

    /** The constructor initialise the variables. */
    public Thermometer(){

        points = 5000;
        firstPlayerCount = 0;
        secondPlayerCount = 0;
        faster = true; // If someone answered correct 5 questions, this variable change to false
        // so the second player can't take points if he answered correct 5 questions too.
    }

    /** Adds the points to the player that answered correct 5 questions first.
     * @param players ,an object of SingleMulti player that represents a player.
     * @param thisPlayer , who player answered.*/
    public void correctAnswer(SingleMultiPlayer players, int thisPlayer){
        if (thisPlayer == 0){
            firstPlayerCount++;
            if (firstPlayerCount == 5) {
                if (faster) {
                    players.getPerson(thisPlayer).setPoints(1000);
                    faster = false;
                }
            }
        }else {
            secondPlayerCount++;
            if (secondPlayerCount == 5){
                if (faster) {
                    players.getPerson(thisPlayer).setPoints(1000);
                    faster = false;
                }
            }
        }
    }

    /** @return true if someone of the players answered correct 5  questions, otherwise returns false. */
    public boolean end(){
        return firstPlayerCount == 5 || secondPlayerCount == 5;
    }

    /** @return the number of question player 1 answered correct. */
    public int getFirstPlayerCount(){
        return firstPlayerCount;
    }

    /** @return the number of question player 2 answered correct. */
    public int getSecondPlayerCount(){
        return secondPlayerCount;
    }
}

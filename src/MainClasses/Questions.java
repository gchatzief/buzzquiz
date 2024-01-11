package MainClasses;

import GUI.Play;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** This Class is responsible to manage the different kinds of Questions.
 * (Loading them, Choosing randomly one every time and deleting them).*/
public class Questions {

    private final File file;
    private final ArrayList<String> category; // An array that keeps the kinds of categories.
    private final ArrayList<String> question; // An array that keeps the questions.
    private final ArrayList<String> answer1; // An array that keeps the first answer.
    private final ArrayList<String> answer2; // An array that keeps the second answer.
    private final ArrayList<String> answer3; // An array that keeps the third answer.
    private final ArrayList<String> answer4; // An array that keeps the forth answer.
    private final ArrayList<String> correctAnswer; // An array that keeps the correct answer.
    private final ArrayList<String> images; // An array that keeps the image's name.
    private ArrayList<Integer> diffNumber;
    private int numberOfQuestion; // A variable that contains a number to a cell of the Arrays to current question.
    private final Play play;
    private final SingleMultiPlayer player;

    /**The constructor initialise the Arrays , and open the file with the questions.*/
    public Questions(Play play, SingleMultiPlayer player) throws FileNotFoundException {


        this.play=play;
        this.player=player;
        file = new File("Questions.txt");
        category =new ArrayList<>();
        question =new ArrayList<>();
        answer1 =new ArrayList<>();
        answer2 =new ArrayList<>();
        answer3 =new ArrayList<>();
        answer4 =new ArrayList<>();
        correctAnswer =new ArrayList<>();
        images = new ArrayList<>();
        diffNumber = new ArrayList<>();
        loadQuestions();
    }

    /** This method is loading the Arrays with the categories/questions/answers...
     * Its time the answers will be in different positions. */
    private void loadQuestions() throws FileNotFoundException {

        String temp; // temporarily string to save every string row of the file.
        String[] tempArray; // temporarily Array of strings to store the pieces of string temp;
        Scanner scan= new Scanner(file);

        while (scan.hasNextLine()) // While there is a row to read from file.
        {

            Random random = new Random();
            int counter = 0;
            int spot; // This variable accepts the random number of the random generator.
            boolean flag; // This boolean variable tells if the value of spot is different than all previous or not.
            String tempCorrectAnswer; // It hold temporarily the right answer.
            diffNumber= new ArrayList<>(); // This Array holds the different values of spot.

            temp="";
            temp= temp.concat(scan.nextLine()); // Take the row of the file.
            tempArray= temp.split("-"); // And separate it.

            //Then add the separate words  to the Arrays.
            category.add(tempArray[0]);
            question.add(tempArray[1]);
            images.add(tempArray[7]);
            tempCorrectAnswer=tempArray[6];

            while (counter < 4) // We need to get 4 answers in total.
            {
                flag=true;
                spot = random.nextInt(4) + 2; // spot value is between ( 2-5 )
                for (Integer integer : diffNumber) { // As big as the array of spot values is.
                    if (integer == spot) // If the Array contains already the random number spot.
                    {
                        flag = false;  // Stop the procedure.
                        break; // Since this value already exists no point to continue searching.
                    }
                }
                if (flag) { // In case the Array don't have the new number of spot.
                    diffNumber.add(spot); // Add the number to Array.
                    /*For every value of counter, the corresponding point of code will be executed.
                    In every if case, a random answer of the total answers will be stored to answer1, answer2, answer3 or answer4.
                    Each value of answer1-4 is different due to different value that spot gets in every loop.
                    Depend the if case, the correct answer will  store A,B,C or D according to if the value that we want to store
                    for an answer is the same with the tempCorrectAnswer we defined before.*/
                    if (counter== 0) {
                        answer1.add(tempArray[spot]); // Add a possible answer to answer 1.
                        if (tempArray[spot].equals(tempCorrectAnswer)) // If this possible answer is the true answer then true answer is A.
                            correctAnswer.add("A");
                    }
                    else if (counter == 1) {
                        answer2.add(tempArray[spot]); // Add a possible answer to answer 2.
                        if (tempArray[spot].equals(tempCorrectAnswer)) // If this possible answer is the true answer then true answer is B.
                            correctAnswer.add("B");
                    }
                    else if (counter == 2) {
                        answer3.add(tempArray[spot]); // Add a possible answer to answer 3.
                        if (tempArray[spot].equals(tempCorrectAnswer)) // If this possible answer is the true answer then true answer is C.
                            correctAnswer.add("C");
                    }
                    else {
                        answer4.add(tempArray[spot]); // Add a possible answer to answer 4.
                        if (tempArray[spot].equals(tempCorrectAnswer)) // If this possible answer is the true answer then true answer is D.
                            correctAnswer.add("D");
                    }
                    counter++; // Increase counter by one, only if the flag is true. (a different answer of total answers has been stored)
                }
            }
        }
    }

    /** This method returns true if the array contains questions in it. In this case it choose a random int from pseudo-random integer generator which represent the spot
     of a question. Other wise returns false.
     @return true if there are questions left , false otherwise. */
    public boolean findRandomQuestion()
    {
        if (question.size()>0) { // In case there are no more questions.
            Random rand = new Random(); //Random int generator function.
            numberOfQuestion = rand.nextInt(question.size()); //Gets the random integer (Between 0 and the size of the array -1 )
            return true;
        }
        return false;
    }

    /** This method checks first if the the answer of the user is acceptable and then if the answer is the correct returns true otherwise returns false. Also in the end
     it deletes the question.
     @param userAnswer , the answer of the user.
     @return true if the the user's answer is correctly, false if it isn't. */
    public boolean isCorrectAnswer(String userAnswer) {

        if (correctAnswer.get(numberOfQuestion).equals(userAnswer)) {
            if (player.getNumberOfPlayers() == 1 || (player.getNumberOfPlayers()==2 && play.isPlayer1_2()))
                remove(); // Delete the question.
            return true;
        }
        if (player.getNumberOfPlayers() == 1 || (player.getNumberOfPlayers()==2 && play.isPlayer1_2()))
            remove(); // Delete the question.
        return false;
    }

    /** @return This method returns the category of a question.*/
    public String getCategory()
    {
        return category.get(numberOfQuestion);
    }

    /** This method prints a question and the possible answers. */
    public String getQuestion()
    {
        return question.get(numberOfQuestion);
    }

    /** @return the right answer. */
    public String getRightAnswer(){
        return correctAnswer.get(numberOfQuestion);
    }

    /** @return the A answer. */
    public String getAnswerA()
    {
        return answer1.get(numberOfQuestion);
    }

    /** @return the A answer. */
    public String getAnswerB()
    {
        return answer2.get(numberOfQuestion);
    }

    /** @return the B answer. */
    public String getAnswerC()
    {
        return answer3.get(numberOfQuestion);
    }

    /** @return the C answer. */
    public String getAnswerD()
    {
        return answer4.get(numberOfQuestion);
    }

    /** @return the images name of the question. */
    public String getImageName() { return images.get(numberOfQuestion); }

    /** This method removes a question which already used. */
    private void remove()
    {
        category.remove(numberOfQuestion);
        question.remove(numberOfQuestion);
        answer1.remove(numberOfQuestion);
        answer2.remove(numberOfQuestion);
        answer3.remove(numberOfQuestion);
        answer4.remove(numberOfQuestion);
        correctAnswer.remove(numberOfQuestion);
        images.remove(numberOfQuestion);
    }

}

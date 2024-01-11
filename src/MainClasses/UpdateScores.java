package MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/** This class updates the scores inside the game byt reading single and multi players files.*/
public class UpdateScores {

    private Scanner scan;
    private final File readSingle;
    private final File readMulti;
    private String updateSingle = "";
    private String updateMulti = "";
    private final ArrayList<String> namesSingle;
    private final ArrayList<Double> pointsSingle;
    private final ArrayList<String> namesMulti;
    private final ArrayList<Integer> winsMulti;
    private final DecimalFormat decimalFormat;

    /** Constructor initialise the variables and creates the file if they dont exests. */
    public UpdateScores() throws IOException {

        decimalFormat = new DecimalFormat("#.#");
        readSingle = new File("singlePlayerScores.doc");
        readMulti = new File("multiPlayersScores.doc");

        if (readSingle.createNewFile()) { System.out.println("Single player file has created.");}
        if (readMulti.createNewFile()) { System.out.println("Multi player file has created.");}

        namesSingle = new ArrayList<>();
        pointsSingle = new ArrayList<>();
        namesMulti = new ArrayList<>();
        winsMulti = new ArrayList<>();

        loadSingle();
        loadMulti();
    }

    /** This Function loads the single player scores. */
    private void loadSingle() throws FileNotFoundException {
        scan= new Scanner(readSingle);
        String tempSingle;
        while (scan.hasNextLine()){

            tempSingle="";
            tempSingle= tempSingle.concat(scan.nextLine()); // Take the row of the file.

            if (!tempSingle.equals("")) {

                String[] tempArray = tempSingle.split("\""); // And separate it.
                namesSingle.add(tempArray[1]);
                pointsSingle.add(Double.parseDouble(tempArray[3]));
            }
        }
        shortTheArraySingle();
        getRidSameSingle();

        for (int i=0; i<namesSingle.size(); i++){
            updateSingle += namesSingle.get(i) + " : " + decimalFormat.format(pointsSingle.get(i)) + "\n";
        }
    }

    /** This Function loads the multi player scores. */
    private void loadMulti() throws FileNotFoundException {
        scan= new Scanner(readMulti);
        String tempMulti;
        while (scan.hasNextLine()){

            tempMulti="";
            tempMulti= tempMulti.concat(scan.nextLine()); // Take the row of the file.
            if (!tempMulti.equals("")) {

                String[] tempArray = tempMulti.split("\""); // And separate it.
                if (!tempArray[1].equals("TIE")) {
                    namesMulti.add(tempArray[1]);
                    winsMulti.add(1);
                }
            }
        }
        getRidSameMulti();
        shortTheArrayMulti();

        for (int i=0; i<namesMulti.size(); i++){
            updateMulti += namesMulti.get(i) + " : " + winsMulti.get(i) + "\n";
        }
    }

    /** This function short the scores of single player from max to min. */
    private void shortTheArraySingle(){

        double temp;
        String temp2;
        for (int i=1; i<pointsSingle.size(); i++){
            for (int j=pointsSingle.size()-1; j>=i; j--) {
                if (pointsSingle.get(j-1) < pointsSingle.get(j)){
                    temp = pointsSingle.get(j-1);
                    pointsSingle.set(j - 1, pointsSingle.get(j));
                    pointsSingle.set(j, temp);
                    temp2 = namesSingle.get(j-1);
                    namesSingle.set(j - 1, namesSingle.get(j));
                    namesSingle.set(j, temp2);
                }
            }
        }
    }

    /** This function short the wins of single player from max to min. */
    private void shortTheArrayMulti(){

        int temp;
        String temp2;
        for (int i=1; i<winsMulti.size(); i++){
            for (int j=winsMulti.size()-1; j>=i; j--) {
                if (winsMulti.get(j-1) < winsMulti.get(j)){
                    temp = winsMulti.get(j-1);
                    winsMulti.set(j - 1, winsMulti.get(j));
                    winsMulti.set(j, temp);
                    temp2 = namesMulti.get(j-1);
                    namesMulti.set(j - 1, namesMulti.get(j));
                    namesMulti.set(j, temp2);
                }
            }
        }
    }

    /** This function keeps the name of the single players only ones. */
    private void getRidSameSingle(){
        for (int i=0; i<namesSingle.size(); i++){
            for (int j=i+1; j<namesSingle.size(); j++){
                if (namesSingle.get(i).equals(namesSingle.get(j)) && i!=j){
                    namesSingle.remove(j);
                    pointsSingle.remove(j);
                    j--;
                }
            }
        }
    }

    /** This function keeps the name of the multi players only ones and add a win when deleted the same person. */
    private void getRidSameMulti(){
        for (int i=0; i<namesMulti.size(); i++){
            for (int j=i+1; j<namesMulti.size(); j++){
                if (namesMulti.get(i).equals(namesMulti.get(j)) && i!=j){
                    winsMulti.set(i, winsMulti.get(i)+1);
                    namesMulti.remove(j);
                    winsMulti.remove(j);
                    j--;
                }
            }
        }
    }

    /** @return the single scores. */
    public String getSingleUpdate(){
        if (!updateSingle.equals(""))
            return updateSingle;
        else
            return "";
    }

    /** @return the multi wins. */
    public String getMultiUpdate(){
        if (!updateMulti.equals(""))
            return updateMulti;
        else
            return "";
    }
}

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


// holds the sentence library for the game
public class Sentences {

    private ArrayList<String> sentences;

    // REQUIRES: .txt file name in a string that contains sentences on each line.
    // MODIFIES: this
    // EFFECTS: copies sentences from file given to an array list that can be
    //          used to prompt the user with sentences
    public void makeSentences(String sentencesFile) {
        String sentence;
        try {
            File sentencesInFile = new File(".\\src\\main\\model\\" + sentencesFile);

            System.out.println(new File(".").getAbsolutePath());
            Scanner reader = new Scanner(sentencesInFile);
            while (reader.hasNextLine()) {
                sentence = reader.nextLine();
                sentences.add(sentence);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file given is not there.");
        }
    }

    // REQUIRES: sentencesFileName to be a file inside model package
    // EFFECTS: makes ArrayList of Sentences from file using makeSentences method
    public Sentences(String sentencesFileName) {
        sentences = new ArrayList<String>();
        makeSentences(sentencesFileName);
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }

    // REQUIRES: non-empty String
    // MODIFIES: this
    // EFFECTS: adds the given sentence to the existing sentences
    public void addSentence(String toAdd) {
        sentences.add(toAdd);
    }

    // EFFECTS: takes a random sentence from the array and provides it.
    public String getRandomSentence() {

        Random rand = new Random();
        int cap = sentences.size();

        return sentences.get(rand.nextInt(cap - 1));

    }

    // EFFECTS: compares 2 strings by comparing each word using compareStrings
    public int pointsForAnswer(String ans, String check) {
        int points = 0;
        ArrayList<String> wordsAns = new ArrayList<>(Arrays.asList(ans.split(" ")));
        ArrayList<String> wordsKey = new ArrayList<>(Arrays.asList(check.split(" ")));

        for (int i = 0; i < wordsKey.size(); i++) {

            try {
                int pointsForWord = compareStrings(wordsKey.get(i), wordsAns.get(i));
                points += pointsForWord;
            } catch (IndexOutOfBoundsException e) {
                // all good
            }


                // end of answer key list, no more words accepte
        }
        return points;
    }

    // EFFECTS: compares 2 strings and adds a point for each character that
    //          gives a point for each sentence that is the same and takes one away
    //          for each one that is incorrect if points > 0
    public int compareStrings(String ans, String check) {
        int points = 0;
        int size;
        int big;

        ArrayList<String> splitAns = convertToArrayList(ans);
        ArrayList<String> splitCheck = convertToArrayList(check);

        size = Math.min(splitAns.size(), splitCheck.size());
        big = Math.max(splitAns.size(), splitCheck.size());

        for (int i = 0; i < size; i++) {
            if (splitAns.get(i).equals(splitCheck.get(i))) {
                points++;
            } else {
                if (points > 0) {
                    points--;
                }
            }
        }
        points -= big - size;
        if (points < 0) {
            points = 0;
        }
        return points;
    }

    // EFFECTS: returns string as an arraylist of 1 characters
    public ArrayList<String> convertToArrayList(String s) {
        String[] splitBefore = s.split("");
        return new ArrayList<String>(Arrays.asList(splitBefore));
    }

}

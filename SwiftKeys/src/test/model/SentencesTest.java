package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;


// Test class for Sentences
public class SentencesTest {
    private Sentences sentences;
    private ArrayList<String> test;

    @BeforeEach
    public void beforeEach() {
        test = new ArrayList<String>();
        sentences = new Sentences("SentencesTesting.txt");
        test.add("He decided water-skiing on a frozen lake wasn’t a good idea.");
        test.add("We have young kids who often walk into our room at night for various reasons including clowns in the closet.");
        test.add("She insisted that cleaning out your closet was the key to good driving.");
        test.add("The random sentence generator generated a random sentence about a random sentence.");
        test.add("Normal activities took extraordinary amounts of concentration at the high altitude.");


    }


    @Test
    public void testGetSentences() {
        assertEquals(test, sentences.getSentences());
    }

    @Test
    public void testAddSentence() {
        test.add("hello");
        sentences.addSentence("hello");
        assertEquals(test, sentences.getSentences());
    }

    @Test
    public void testGetRandomSentence() {
        String contained = sentences.getRandomSentence();

        assertTrue(testGetRandomSentenceHelper(contained));
    }

    public boolean testGetRandomSentenceHelper(String contained) {
        for (String s : sentences.getSentences()) {
            if (s.equals(contained)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testPointsForAnswer() {
        String s1 = "word1 word2";

        String t1 = "word1 word2";
        String t2 = "word 1word2";
        String t3 = "word word";

        assertEquals(10, sentences.pointsForAnswer(s1, t1));
        assertEquals(3, sentences.pointsForAnswer(s1, t2));
        assertEquals(6, sentences.pointsForAnswer(s1, t3));

    }

    @Test
    public void testCompareStrings() {
        String s1 = "hello";
        String s2 = "helli";
        String s3 = "hell";
        String s4 = "helloo";
        String s5 = "thisiswrong";

        assertEquals(5, sentences.compareStrings(s1, s1));
        assertEquals(3, sentences.compareStrings(s2, s1));
        assertEquals(3, sentences.compareStrings(s3, s1));
        assertEquals(3, sentences.compareStrings(s1, s3));
        assertEquals(4, sentences.compareStrings(s4, s1));
        assertEquals(0, sentences.compareStrings(s1, s5));




    }

    @Test
    public void testConvertToArrayList() {
        ArrayList<String> testString = sentences.convertToArrayList("hello");
        ArrayList<String> compare = new ArrayList<String>();
        compare.add("h");
        compare.add("e");
        compare.add("l");
        compare.add("l");
        compare.add("o");

        assertEquals(compare, testString);
    }



}

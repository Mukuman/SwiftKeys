package model;


import model.exceptions.PlayerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

// test class for roster
public class RosterTest {

    Player p1;
    Player p2;
    Player p3;

    Roster rosterTest;

    @BeforeEach
    public void beforeEach() {
        p1 = new Player("p1");
        p2 = new Player("p2");
        p3 = new Player("p3");

        rosterTest = new Roster("test");

    }

    @Test
    public void testGetName() {
        assertEquals("test", rosterTest.getName());
    }

    @Test
    public void testGetRoster() {
        assertEquals(new ArrayList<Player>(), rosterTest.getRoster());
    }

    @Test
    public void testGetLeaderboard() {
        assertEquals(new ArrayList<Player>(), rosterTest.getLeaderboard());
    }

    @Test
    public void testAddPlayer() {
        rosterTest.addPlayer(p1);

        assertEquals(p1, rosterTest.getRoster().get(0));
        assertEquals(p1, rosterTest.getLeaderboard().get(0));

        rosterTest.addPlayer(p2);

        assertEquals(p2, rosterTest.getRoster().get(1));
        assertEquals(p2, rosterTest.getLeaderboard().get(1));
    }

    @Test
    public void testUpdatePlayer() {


        rosterTest.addPlayer(p1);
        p1.addPoints(10);
        p1.newGame();
        int pos = rosterTest.updatePlayer(p1);

        assertEquals(1, pos);
        assertEquals(p1, rosterTest.getLeaderboard().get(0));


        rosterTest.addPlayer(p2);
        rosterTest.addPlayer(p3);


        p2.addPoints(20);
        p2.newGame();

        pos = rosterTest.updatePlayer(p2);

        assertEquals(1, pos);
        assertEquals(p2, rosterTest.getLeaderboard().get(0));
        assertNotEquals(p2, rosterTest.getLeaderboard().get(2));

        p1.addPoints(15);
        p1.newGame();
        pos = rosterTest.updatePlayer(p1);

        assertEquals(2, pos);
        assertEquals(p1, rosterTest.getLeaderboard().get(1));

        p3.addPoints(100);
        p3.newGame();
        pos = rosterTest.updatePlayer(p3);

        assertEquals(1, pos);
        assertEquals(p3, rosterTest.getLeaderboard().get(0));
        assertEquals(3, rosterTest.getLeaderboard().size());
    }

    @Test
    public void testPrintLeaderboard() {
        rosterTest.addPlayer(p1);
        rosterTest.addPlayer(p2);
        rosterTest.addPlayer(p3);
        String result = "Current Leaderboard:";
        result = result + "1. p1 with a score of 0";
        result = result + "2. p2 with a score of 0";
        result = result + "3. p3 with a score of 0";

        assertEquals(result, rosterTest.printLeaderboard());
    }


    @Test
    public void testPrintRoster() {
        rosterTest.addPlayer(p1);
        rosterTest.addPlayer(p2);
        rosterTest.addPlayer(p3);

        String testString = rosterTest.printRoster();

        String checkString = "Available players: ";
        checkString = checkString + "1. p1";
        checkString = checkString + "2. p2";
        checkString = checkString + "3. p3";

        assertEquals(checkString, testString);
    }

    @Test
    public void testRemovePlayer() {
        rosterTest.addPlayer(p1);
        rosterTest.addPlayer(p2);

        try {
            rosterTest.removePlayer("p1");
        } catch (PlayerNotFoundException e) {
            fail("Unexpected PlayerNotFound exception");
        }

        assertEquals(p2, rosterTest.getRoster().get(0));
        assertEquals(1, rosterTest.getRoster().size());
        assertEquals(1, rosterTest.getLeaderboard().size());

        try {
            rosterTest.removePlayer("p3");
            fail("Player is not in roster and expected Exception.");
        } catch (PlayerNotFoundException e) {
            // all good
        }



    }


}

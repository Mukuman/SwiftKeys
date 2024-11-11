package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test class for player
public class PlayerTest {
    private Player player;

    @BeforeEach
    public void runBefore() {
        player = new Player("test");
    }

    @Test
    public void testGetName() {
        assertEquals("test", player.getName());
    }

    @Test
    public void testchangeName() {
        player.changeName("test2");
        assertEquals("test2", player.getName());
    }

    @Test
    public void testGetPoints() {
        assertEquals(0, player.getCurrentPoints());
        player.addPoints(5);
        assertEquals(5, player.getCurrentPoints());

    }

    @Test
    public void testAddPoints() {
        player.addPoints(10);
        assertEquals(10, player.getCurrentPoints());
    }

    @Test
    public void testGetHighscore() {
        assertEquals(0, player.getHighscore());

        player.addPoints(10);
        player.newGame();

        assertEquals(10, player.getHighscore());

    }

    @Test
    public void testNewGame(){
        player.addPoints(100);
        player.newGame();

        assertEquals(100, player.getHighscore());
        assertEquals(0, player.getCurrentPoints());

        player.addPoints(99);
        player.newGame();

        assertEquals(100, player.getHighscore());
    }

}
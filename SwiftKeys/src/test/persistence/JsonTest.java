package persistence;

import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

// to check if a certain fields are the same as the player given
public class JsonTest {

    protected void checkPlayer(int cp, int hs, String name, Player p) {
        assertEquals(cp, p.getCurrentPoints());
        assertEquals(hs, p.getHighscore());
        assertEquals(name, p.getName());
    }
}

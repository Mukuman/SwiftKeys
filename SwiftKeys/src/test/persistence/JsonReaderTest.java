package persistence;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import model.Roster;
import model.Player;

import static org.junit.jupiter.api.Assertions.*;


// test class for Json Reader
public class JsonReaderTest extends JsonTest{


    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Roster roster = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyRoster() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRoster.json");
        try {
            Roster roster = reader.read();
            assertEquals("My roster", roster.getName());
            assertEquals(0, roster.getRoster().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRoster() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRoster.json");
        try {
            Roster roster = reader.read();
            assertEquals("My roster", roster.getName());
            List<Player> players = roster.getRoster();
            assertEquals(2, players.size());
            checkPlayer(0, 50, "mukund", players.get(0));
            checkPlayer(0, 100, "jackson", players.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}

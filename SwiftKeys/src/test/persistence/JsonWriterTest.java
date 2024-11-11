package persistence;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import model.Roster;
import model.Player;

import static org.junit.jupiter.api.Assertions.*;

// test file for JsonWriter
public class JsonWriterTest extends JsonTest{

    @Test
    public void testWriterInvalidFile() {
        try {
            Roster roster = new Roster("My roster");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyRoster() {
        try {
            Roster roster = new Roster("My roster");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRoster.json");
            writer.open();
            writer.write(roster);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRoster.json");
            roster = reader.read();
            assertEquals("My roster", roster.getName());
            assertEquals(0, roster.getRoster().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralRoster() {
        try {
            Roster roster = new Roster("My roster");
            roster.addPlayer(new Player("mukund", 0, 50));
            roster.addPlayer(new Player("jackson", 0, 100));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRoster.json");
            writer.open();
            writer.write(roster);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRoster.json");
            roster = reader.read();
            List<Player> players = roster.getRoster();
            assertEquals("My roster", roster.getName());
            checkPlayer(0, 50, "mukund", players.get(0));
            checkPlayer(0, 100, "jackson", players.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

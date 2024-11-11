package persistence;

import model.Roster;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// reads Json file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Roster read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRoster(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses roster from JSON object and returns it
    private Roster parseRoster(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Roster roster = new Roster(name);
        addPlayers(roster, jsonObject);
        return roster;
    }

    // MODIFIES: roster
    // EFFECTS: parses players from JSON object and adds them to roster
    private void addPlayers(Roster roster, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(roster, nextPlayer);
        }
    }

    // MODIFIES: roster
    // EFFECTS: parses player from JSON object and adds it to roster
    private void addPlayer(Roster roster, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int currentPoints = jsonObject.getInt("currentPoints");
        int highScore = jsonObject.getInt("highscore");

        Player player = new Player(name, currentPoints, highScore);
        roster.addPlayer(player);
        roster.updatePlayer(player);
    }
}





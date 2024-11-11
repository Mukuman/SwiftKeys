package model;

import model.exceptions.PlayerNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;

// representation of a roster with a leaderboard as well of players
public class Roster implements Writable {

    private String name;

    private ArrayList<Player> roster;

    private ArrayList<Player> leaderboard;

    // MODIFIES: this
    // EFFECTS: instantiates Roster
    public Roster(String name) {
        this.roster = new ArrayList<Player>();
        this.leaderboard = new ArrayList<Player>();
        this.name = name;
    }

    public ArrayList<Player> getRoster() {
        return roster;
    }

    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }

    public String getName() {
        return this.name;
    }

    // REQUIRES: player must not already be in the roster and must have an initial score of 0
    // MODIFIES: this
    // EFFECTS: adds a new player to the roster and puts them on the bottom of the leaderboard
    public void addPlayer(Player player) {
        roster.add(player);
        leaderboard.add(player);
        EventLog.getInstance().logEvent(new Event("Added " + player.getName()));
    }

    // REQUIRES: player that exists in the roster
    // MODIFIES: this
    // EFFECTS: updates players position on the leaderboard depending on their highscore and returns position

    public int updatePlayer(Player player) {
        int highscore = player.getHighscore();
        int pos = 0;
        for (int i = 0; i < leaderboard.size(); i++) {
            Player thisPlayer = leaderboard.get(i);
            if (player.equals(thisPlayer)) {
                leaderboard.set(i, player); // in case the player has a higher score but not enough
                                            // to get to the next position
                pos = i + 1;
                break;
            }

            int currHighscore = leaderboard.get(i).getHighscore();

            if (highscore > currHighscore) {
                leaderboard.add(i, player);

                for (int a = i + 1; a < leaderboard.size(); a++) {
                    if (player.equals(leaderboard.get(a))) {
                        leaderboard.remove(a);
                        pos = i + 1;
                        i = leaderboard.size();
                        break;
                    }
                }

            }
        }
        // if the requires is followed this case will never run.
        return pos;
    }

    // EFFECTS: prints a leaderboard to the screen and returns joined string of what was printed
    public String printLeaderboard() {
        String joined = "";
        String name;
        String score;

        System.out.println("Current Leaderboard:");
        joined = joined + "Current Leaderboard:";

        for (int i = 0; i < leaderboard.size(); i++) {
            name = (i + 1) + ". " + leaderboard.get(i).getName();
            score = " with a score of " + leaderboard.get(i).getHighscore();
            System.out.print(name);
            System.out.println(score);
            joined = joined + name + score;
        }

        return joined;
    }


    //EFFECTS: prints roster for player choosing and returns joined string printed
    public String printRoster() {
        String joined = "Available players: ";
        String line = "";
        System.out.println("Available players: ");

        int count = 0;
        for (Player p: roster) {
            count++;
            line = count + ". " + p.getName();
            joined = joined + line;
            System.out.println(line);
        }

        return joined;
    }

    // MODIFIES: this
    // EFFECTS: if given player with the name exists in method, removes player. Otherwise throws
    // player not in roster exception.
    public void removePlayer(String p) throws PlayerNotFoundException {
        for (int i = 0; i < roster.size(); i++) {
            Player check = roster.get(i);
            if (check.getName().equals(p)) {
                roster.remove(i);
                leaderboard.remove(check);
                EventLog.getInstance().logEvent(new Event("Removed " + p));
                return;
            }
        }
        throw new PlayerNotFoundException();
    }



    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS: returns players in this roster as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : roster) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}

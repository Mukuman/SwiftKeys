package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a player with a name, current points, and highscore.
public class Player implements Writable {

    private String name;

    private int currentPoints;
    private int highscore;

    // MODIFIES: this
    // EFFECTS: instantiates Player
    public Player(String name) {
        this.name = name;
        this.currentPoints = 0;
        this.highscore = 0;
    }

    public Player(String name, int currentPoints, int highScore) {
        this.name = name;
        this.currentPoints = currentPoints;
        this.highscore = highScore;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void addPoints(int toAdd) {
        currentPoints += toAdd;
    }

    public int getHighscore() {
        return highscore;
    }

    // MODIFIES: this
    // EFFECTS: signal that player has started a new game
    //          checks to see if current points are a new highscore
    //          and will change highscore if so, while resetting points to 0.
    public void newGame() {
        if (currentPoints > highscore) {
            highscore = currentPoints;
        }
        currentPoints = 0;
    }



    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("currentPoints", currentPoints);
        json.put("highscore", highscore);
        return json;
    }

}

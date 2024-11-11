package ui;

import model.Sentences;
import model.Player;
import model.Roster;
import model.exceptions.PlayerNotFoundException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;




import static java.util.Objects.isNull;

// SwiftKeys application model class
public class SwiftKeys {


    private static final String JSON_STORE = "./data/roster.json";
    private Sentences sentences;
    private Roster roster;
    private Player thisPlayer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public Roster getRoster() {
        return roster;
    }

    // EFFECTS: runs the game
    public SwiftKeys() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        sentences = new Sentences("funnytime.txt");
        roster = new Roster("Mukund's Game");
    }

    // MODIFIES: this
    // EFFECTS: runs game cycle
    private void startGame() {

        sentences = new Sentences("funnytime.txt");
        roster = new Roster("Mukund's Game");

        boolean keepGoing = true;

        // introduction text for the game
        introduction();

        while (keepGoing) {

            // options regarding game
            String ans = proccessCommand();
            if (ans.equals("4")) {
                keepGoing = false;
                continue;
            }

            // choose a player
            thisPlayer = choosePlayer();

            // big method to run the game
            runGame(thisPlayer);

        }
    }




    //EFFECTS: prints introduction to the console
    private void introduction() {
        System.out.println("Welcome to SwiftKeys!");
        System.out.println("In this game, you will be able to create a profile, "
                + "where you will then be able to try and type the sentence as fast as possible.");
        System.out.println("You are able to add as many profiles as you would like.");
    }

    // EFFECTS: displays selection menu
    private String selectionMenu() {
        Scanner input = new Scanner(System.in);
        if (roster.getRoster().size() == 0) {
            return "6";
        } else {
            roster.printLeaderboard();
            System.out.println("\nOptions:");
            System.out.println("\t[1] --> to start");
            System.out.println("\t[2] --> load game from a file");
            System.out.println("\t[3] --> save game to file");
            System.out.println("\t[4] --> End the game");
            System.out.println("\t[5] --> Remove a player");
            System.out.println("\t[6] --> Add another player");
        }
        return input.nextLine();
    }


    // EFFECTS: processes commands and returns input for console version
    private String proccessCommand() {
        String ans;

        while (true) {
            ans = selectionMenu();

            if (ans.equals("1")) {
                break;
            } else if (ans.equals("2")) {
                loadGame();
            } else if (ans.equals("3")) {
                saveGame();
            } else if (ans.equals("4")) {
                break;
            } else if (ans.equals("5")) {
                removeAPlayer();
            } else if (ans.equals("6")) {
                addAPlayer();
            }
        }
        return ans;
    }

    // EFFECTS: processes given command for GUI version
    public void proccessCommandGUI(String ans, SwiftKeysControl ui) {

        if (ans.equals("1")) {
            if (roster.getRoster().size() == 0) {
                System.out.println("Please add a player first!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
            thisPlayer = choosePlayer();
            runGame(thisPlayer);
        } else if (ans.equals("2")) {
            loadGame();
        } else if (ans.equals("3")) {
            saveGame();
        } else if (ans.equals("5")) {
            removePlayerGUI(ui);
        } else if (ans.equals("6")) {
            addPlayerGUI(ui);
        }

    }

    // MODIFIES: this
    // EFFECTS: Adds a player for console version
    private void addAPlayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the new player you want to add: ");
        String ans = input.nextLine();
        Player playerToAdd = new Player(ans);

        if (checkPlayer(playerToAdd)) {
            roster.addPlayer(playerToAdd);
        } else {
            System.out.println("That player already exists! Try again.\n");
            addAPlayer();
        }
    }

    // EFFECTS: adds a player for GUI version pt. 1
    private void addPlayerGUI(SwiftKeysControl ui) {
        EditLbGUI apGUI = new EditLbGUI(this, ui, true);
    }

    // MODIFIES: this
    // EFFECTS: adds a player for GUI version pt. 2
    public void addPlayerGUI2(EditLbGUI apGUI, SwiftKeysControl ui) {
        Player playerToAdd = new Player(apGUI.getPlayer());
        if (checkPlayer(playerToAdd)) {
            roster.addPlayer(playerToAdd);
            ui.getView().refreshLb(roster.getLeaderboard());
            ui.getView().setVisible(true);

        }
    }

    // MODIFIES: this
    // EFFECTS: removes a player for console version
    private void removeAPlayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the player you want to remove: ");
        String player = input.nextLine();

        try {
            roster.removePlayer(player);
        } catch (PlayerNotFoundException e) {
            System.out.println("That player doesn't exist in the roster!\n");
        }
    }

    // EFFECTS: removes a player for GUI version pt. 1
    private void removePlayerGUI(SwiftKeysControl sk) {
        EditLbGUI rpGUI = new EditLbGUI(this, sk, false);
    }

    // MODIFIES: this
    // EFFECTS: removes a player for GUI version pt. 2
    public void removePlayerGUI2(EditLbGUI rpGUI, SwiftKeysControl ui) throws PlayerNotFoundException {
        String player = rpGUI.getPlayer();
        try {
            roster.removePlayer(player);
            ui.getView().refreshLb(roster.getLeaderboard());
        } catch (PlayerNotFoundException e) {
            System.out.println("Player not in roster");
        } finally {
            ui.getView().setVisible(true);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadGame() {
        try {
            roster = jsonReader.read();
            System.out.println("Loaded " + roster.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(roster);
            jsonWriter.close();
            System.out.println("Saved " + roster.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // EFFECTS: returns true if the player is not in the roster
    private boolean checkPlayer(Player p) {
        for (Player check : roster.getRoster()) {
            if (p.getName().equals(check.getName())) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: prompts the user to ask for a string and takes the string to see if it is in the roster
    //          if it is, it returns the player. If incorrect, it informs the player and prompts again.
    private Player choosePlayer() {
        Scanner input = new Scanner(System.in);
        boolean playerNotChosen = true;
        Player willPlay = null;
        ArrayList<Player> options = roster.getRoster();

        roster.printRoster();

        while (playerNotChosen) {

            System.out.print("Please enter the name of the player you would like to play as: ");
            String checkPlayer = input.nextLine();


            for (Player p: options) {
                if (checkPlayer.equals(p.getName())) {
                    willPlay = p;
                    playerNotChosen = false;
                    break;
                }
            }

            if (isNull(willPlay)) {
                System.out.println("Name is not in the list, please try again.");
            }

        }

        return willPlay;
    }

    // MODIFIES: this
    // EFFECTS: runs an instance of the game for a player
    private void runGame(Player player) {
        boolean sentencesLeft = true;
        Scanner input = new Scanner(System.in);
        long timeLimit = 30000;
        long startTime = System.currentTimeMillis();;
        long endTime = startTime + timeLimit;
        String sentence;
        String ans;
        while (sentencesLeft) {

            System.out.println("Retype the following sentence as fast as possible");
            sentence = sentences.getRandomSentence();
            System.out.println(sentence);
            ans = input.nextLine();
            if (endTime < System.currentTimeMillis()) {
                System.out.println("Times up!, you finished with " + player.getCurrentPoints() + " points!\n");
                player.newGame();
                System.out.println("You are number " + roster.updatePlayer(player) + " on the leaderboard!\n");

                sentencesLeft = false;
                continue;
            }

            int points = sentences.pointsForAnswer(ans, sentence);
            player.addPoints(points);
            System.out.println("You won " + points + " points\n");
        }
    }

}

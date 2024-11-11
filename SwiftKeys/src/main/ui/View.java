package ui;

import model.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

// view class for GUI
public class View extends JFrame {

//    public static void main(String[] args) {
//        new View();
//    }


    private final Color darkGrey = new Color(0x282c34);
    private JButton button;
    private JLabel startingLabel;
    private JButton start;
    private JButton load;
    private JButton save;
    private JButton end;
    private JButton remove;
    private JButton add;
    private ArrayList<JButton> menuButtons = new ArrayList<>();
    private Box lbHolder;
    private JPanel leaderboardPanel;
    Box startingMenu;



    public View() {
        start = new JButton("start");
        load = new JButton("load");
        save = new JButton("save");
        end = new JButton("end");
        remove = new JButton("remove");
        add = new JButton("add");
        leaderboardPanel = new JPanel();
        addButtons();
    }


    public JButton getButton() {
        return button;
    }

    public JButton getStart() {
        return start;
    }

    public JButton getLoad() {
        return load;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getEnd() {
        return end;
    }

    public JButton getRemove() {
        return remove;
    }

    public JButton getAdd() {
        return add;
    }


    // MODIFIES: this
    //EFFECTS: makes the frame
    public void instantiateFrame() {
        setTitle("SwiftKeys");
        getContentPane().setBackground(Color.BLACK);
        setSize(750, 750);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // MODIFIES: this
    // EFFECTS: creates intro screen
    public void introScreen() {
        startingMenu = new Box(BoxLayout.Y_AXIS);

        JPanel show = new JPanel();
        show.setLayout(new BoxLayout(show, BoxLayout.PAGE_AXIS));

        createIntroLabel();
        startGameButton();
        show.add(startingLabel);
        startingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        show.add(Box.createVerticalGlue());
        show.add(button);
        show.setBackground(Color.BLACK);
        startingMenu.add(Box.createVerticalGlue());
        startingMenu.add(show);
        startingMenu.add(Box.createVerticalGlue());
        show.setAlignmentX(Component.CENTER_ALIGNMENT);
        startingMenu.setBackground(Color.BLACK);

        add(startingMenu);
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: creates introduction label
    public void createIntroLabel() {
//        ImageIcon ic = new ImageIcon("./data/swiftKeysLogo.jpg");
        ImageIcon ic = new ImageIcon("./data/swiftKeys.png");

        startingLabel = new JLabel(ic);
//        startingLabel = new JLabel();
//        startingLabel.setText("Welcome to SwiftKeys");
//        startingLabel.setForeground(Color.LIGHT_GRAY);
//        startingLabel.setFont(new Font("MV Boli", Font.ITALIC, 20));
//        startingLabel.setHorizontalAlignment(JLabel.CENTER);
//        startingLabel.setBounds(237, 262, 275, 50);

//        startingLabel.setBorder(border);
        startingLabel.setPreferredSize(new Dimension(250, 100));



    }

    // EFFECTS: Creates button to start
    public void startGameButton() {
        button = new JButton();
        button.setPreferredSize(new Dimension(300, 50));
        button.setText("Press to play");
        button.setFocusable(false);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
//        button.setBorder(border);

    }

    // MODIFIES: this
    // EFFECTS: method sequence after you click the start button
    public void introClicked(ArrayList<Player> lb) {
        getContentPane().setBackground(darkGrey);
        remove(startingMenu);
        remove(button);
        setLayout(new GridLayout(1, 2));
        selectionMenu();
        leaderboardPanels(lb);

    }

    // MODIFIES: this
    // EFFECTS: updates the leaderboard with new ArrayList
    public void refreshLb(ArrayList<Player> lb) {
        leaderboardPanel.removeAll();
        JLabel header = new JLabel("Leaderboard");
        formatLabelLb(header);
        header.setFont(new Font("Comic Sans MS\n", Font.ITALIC, 40));
        leaderboardPanel.add(header);

        int count = 0;
        for (Player p: lb) {
            count++;
            JLabel tempLabel = new JLabel(count + "." + p.getName() + " " + p.getHighscore());
            tempLabel.setBounds(leaderboardPanel.getBounds());
            formatLabelLb(tempLabel);
            leaderboardPanel.add(tempLabel);
        }
        leaderboardPanel.setBackground(darkGrey);
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));


    }

    // MODIFIES: this
    // leaderboard panel
    public void leaderboardPanels(ArrayList<Player> lb) {
        lbHolder = new Box(BoxLayout.Y_AXIS);

        refreshLb(lb);
        lbHolder.add(Box.createVerticalGlue());
        lbHolder.add(leaderboardPanel);
        leaderboardPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        lbHolder.add(Box.createVerticalGlue());
        add(lbHolder);

    }


    // MODIFIES: this
    // EFFECTS: renders selection menu
    public void selectionMenu() {

        Box menu = new Box(BoxLayout.Y_AXIS);

        JPanel options = new JPanel();
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
        options.setBackground(darkGrey);

        for (JButton button: menuButtons) {
            button.add(Box.createRigidArea(new Dimension(150, 30)));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            formatButton(button);
            options.add(button);
        }
        menu.add(Box.createVerticalGlue());
        menu.add(options);
        menu.add(Box.createVerticalGlue());
        options.setAlignmentX(Component.CENTER_ALIGNMENT);


        add(menu);
    }

    // MODIFIES: this
    // EFFECTS: renders selection menu
    public void addButtons() {
        menuButtons.add(start);
        menuButtons.add(load);
        menuButtons.add(save);
        menuButtons.add(end);
        menuButtons.add(remove);
        menuButtons.add(add);
    }

    // MODIFIES: this
    // EFFECTS: formats the given button to look right
    public void formatButton(JButton button) {
        button.setFocusable(false);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.darkGray);
        button.setSize(new Dimension(125, 30));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    // MODIFIES: this
    // EFFECTS: modifies label to look nice
    public void formatLabelLb(JLabel label) {
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        label.setHorizontalAlignment(JLabel.LEFT);
    }

    // MODIFIES: this
    // EFFECTS: Adds listener class instance to each button
    public void addListenerToButtons(SwiftKeysControl control) {
        button.addActionListener(control);
        for (JButton b : menuButtons) {
            b.addActionListener(control);
        }
    }

}

package ui;

import model.exceptions.PlayerNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class for inputting a text field that can add/remove something from the GUI
public class EditLbGUI extends JFrame implements ActionListener {

    private JButton submit;
    private JTextField tf;
    private String player;
    private SwiftKeys sk;
    private SwiftKeysControl ui;
    private boolean addOrRemove;



    public EditLbGUI(SwiftKeys sk, SwiftKeysControl ui, boolean aor) {
        this.sk = sk;
        this.ui = ui;
        this.addOrRemove = aor;
        setTitle("Add or Remove a player");
        createFrame();
        createTextField();
        createSubmit();
        pack();
        setVisible(true);
    }

    public String getPlayer() {
        return player;
    }

    public void createFrame() {
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

    }

    public void createTextField() {
        tf = new JTextField();
        tf.setPreferredSize(new Dimension(250, 40));
        add(tf);
    }

    public void createSubmit() {
        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.setBackground(Color.lightGray);
        submit.setSize(40, 200);
        submit.addActionListener(this);
        add(submit);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        player = tf.getText();
        setVisible(false);
        if (addOrRemove) {
            sk.addPlayerGUI2(this, ui);
        } else {
            try {
                sk.removePlayerGUI2(this, ui);
            } catch (PlayerNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }


    }
}

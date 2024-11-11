package ui;

import model.Event;
import model.EventLog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


// control class for GUI
public class SwiftKeysControl implements ActionListener {
    public View getView() {
        return view;
    }

    private View view;
    private SwiftKeys swiftKeys;

    public SwiftKeysControl() {
        view = new View();
        swiftKeys = new SwiftKeys();

        view.instantiateFrame();
        view.introScreen();


        this.view.addListenerToButtons(this);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getButton()) {
            view.introClicked(swiftKeys.getRoster().getLeaderboard());
        }
        actionChooserMenuButtons(e);
        view.repaint();
        view.revalidate();

    }

    public void actionChooserMenuButtons(ActionEvent e) {
        String ans = "";
        if (e.getSource() == view.getStart()) {
            ans = "1";
        } else if (e.getSource() == view.getLoad()) {
            ans = "2";
        } else if (e.getSource() == view.getSave()) {
            ans = "3";
        } else if (e.getSource() == view.getEnd()) {
            printLog();
            System.exit(0);
        } else if (e.getSource() == view.getRemove()) {
            ans = "5";
        } else if (e.getSource() == view.getAdd()) {
            ans = "6";
        }
        view.setVisible(false);
        if (ans.equals("5") || ans.equals("6")) {
            swiftKeys.proccessCommandGUI(ans, this);
        } else {
            swiftKeys.proccessCommandGUI(ans, this);
            view.refreshLb(swiftKeys.getRoster().getLeaderboard());
            view.setVisible(true);
        }

    }

    private void printLog() {
        EventLog el = EventLog.getInstance();

        for (Event next : el) {
            System.out.println(next.getDescription());
        }
    }
}


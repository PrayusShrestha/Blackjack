package ui;

import model.Player;
import persistence.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
 * Represents the GUI in which players can choose to play or learn
 */
public class ChoiceGUI extends JFrame implements ActionListener {
    private Player player;
    static final String ROUTE_TO_DATA = "./data/players.json";
    private Reader reader;

    private JLabel label;
    private JFrame frame;
    private JButton goToLearn;
    private JButton goToPlay;
    private JPanel panel;
    private String pname;

    // Sets up ChoiceGUI
    public ChoiceGUI(String n) {
        super("Blackjack UI");
        this.pname = n;

        frame = new JFrame();
        label = new JLabel("");
        panel = new JPanel();

        // Code block below uses information gathered from:
        // https://stackoverflow.com/questions/5911565/how-to-add-multiple-actionlisteners-for-multiple-buttons-in-java-swing
        goToLearn = new JButton(new AbstractAction("Learn") {
            // MODIFIES: label
            // EFFECTS: shows link to learn how to play
            @Override
            public void actionPerformed(ActionEvent e) {
                String link = "https://bicyclecards.com/how-to-play/blackjack/";    // Credits to Bicycle Cards for this tutorial
                String message = "Here's a helpful link to learn the game: " + link;
                label.setText(message);
            }
        });
        goToPlay = new JButton(new AbstractAction("Play") {
            // MODIFIES: frame
            // EFFECTS: gets ready to play game
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                setupPlayer(pname);
            }
        });

        adding();
    }

    // MODIFIES: player
    // EFFECTS: sets up player and send to next GUI
    private void setupPlayer(String name) {
        reader = new Reader(ROUTE_TO_DATA);
        try {
            int bankroll = reader.read(name);
            player = new Player(name, bankroll);
        } catch (Exception e) {
            player = new Player(name, 0);
        }

        if (player.getBankroll() == 0) {
            new SetBankrollGUI(player);
        } else {
            new SetBetSize(player);
        }
    }

    // MODIFIES: panel, frame
    // EFFECTS: adds Swing elements to each other (setup)
    private void adding() {
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(goToLearn);
        panel.add(goToPlay);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        goToPlay.addActionListener(this);
        goToLearn.addActionListener(this);

    }


    // MODIFIES: label
    // EFFECTS: shows link to learn how to play
    @Override
    public void actionPerformed(ActionEvent e) {
        String link = "https://bicyclecards.com/how-to-play/blackjack/";    // Credits to Bicycle Cards for this tutorial
        String message = "Here's a helpful link to learn the game: " + link;
        label.setText(message);
    }
}
















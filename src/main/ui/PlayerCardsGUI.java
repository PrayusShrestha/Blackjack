package ui;

import model.Player;

import javax.swing.*;
import java.awt.*;

/*
 * Represents GUI with the player's cards
 */
public class PlayerCardsGUI extends JFrame {
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private Player player;

    // Sets up PlayerCardsGUI
    public PlayerCardsGUI(Player player) {
        super("Blackjack UI");
        this.player = player;

        frame = new JFrame();
        panel = new JPanel();

        String message = getCards(player);
        label = new JLabel(message);
        adding();
    }

    // EFFECTS: gets all of the player's cards
    private String getCards(Player player) {
        String output = "<html> Your Cards: <br>";
        for (int i = 0; i < player.getHand().size(); i++) {
            output += player.getHand().get(i).getName() + " of " + player.getHand().get(i).getSuit();
            output += "<br>";
        }

        output += "</html>";
        return output;
    }


    // EFFECTS: sets up Swing elements
    private void adding() {
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: closes frame
    public void exit() {
        frame.dispose();
    }
}

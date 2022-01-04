package ui;

import model.Dealer;

import javax.swing.*;
import java.awt.*;

public class DealerCardsGUI extends JFrame {
    private JLabel label;
    private JFrame frame;
    private JLabel upLabel;
    private JPanel panel;
    private Dealer dealer;
    private String allCards;
    private JFrame allFrame;
    private JPanel allPanel;

    // Sets up the DealerCardsGUI
    public DealerCardsGUI(Dealer dealer) {
        super("Blackjack UI");
        this.dealer = dealer;

        frame = new JFrame();
        allFrame = new JFrame();
        panel = new JPanel();
        allPanel = new JPanel();

        allCards = getCards(dealer);
        String upCard = getUpCard(dealer);
        upLabel = new JLabel(upCard);
        label = new JLabel(allCards);
        adding();
    }

    // EFFECTS: shows upcard
    private String getUpCard(Dealer dealer) {
        String output = "<html> Dealer's Upcard: <br>";
        output += dealer.getHand().get(1).getName() + " of " + dealer.getHand().get(1).getSuit();
        output += "</html>";
        return output;
    }

    // EFFECTS: shows all cards
    private String getCards(Dealer dealer) {
        String output = "<html> Dealer's Cards: <br>";
        for (int i = 0; i < dealer.getHand().size(); i++) {
            output += dealer.getHand().get(i).getName() + " of " + dealer.getHand().get(i).getSuit();
            output += "<br>";
        }

        output += "</html>";
        return output;
    }


    // MODIFIES: panel, frame
    // EFFECTS: adds and sets up Swing elements
    private void adding() {
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(upLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: frame, panel
    // EFFECTS: switches to showing all cards
    public void switchToAll() {
        exitFrame();
        allPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        allPanel.setLayout(new GridLayout(0,1));

        allPanel.add(label);
        allFrame.add(allPanel, BorderLayout.CENTER);
        allFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        allFrame.pack();
        allFrame.setVisible(true);

    }


    // MODIFIES: this
    // EFFECTS: closes frame
    public void exitFrame() {
        frame.dispose();
    }

    // MODIFIES: this
    // EFFECTS: closes allFrame
    public void exitAllFrame() {
        allFrame.dispose();
        allFrame.setVisible(false);
        System.out.println("Hit");
    }
}

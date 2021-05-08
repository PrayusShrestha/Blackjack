package ui;

import model.Card;
import model.Dealer;
import model.Deck;
import model.Player;
import persistence.Writer;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static ui.Blackjack.ROUTE_TO_DATA;
import static ui.Blackjack.SMALLEST_PLAYABLE_DECK_SIZE;

/*
 * Represents the GUI in which players' decide on hit vs stand
 */


public class PlayGUI extends JFrame implements ActionListener {
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private Writer writer;

    private JLabel label;
    public static JFrame frame;
    private JButton hitButton;
    private JButton standButton;
    private JPanel panel;
    private PlayerCardsGUI playerGui;
    private DealerCardsGUI dealerGUI;
    private ResultGUI resultGUI;

    public PlayGUI(Player player) {
        this.player = player;
        dealer = new Dealer();
        deck = new Deck();
        deck.addDeck();

        frame = new JFrame();
        label = new JLabel("");
        panel = new JPanel();

        hitButton = new JButton(new AbstractAction("Hit") {
            // MODIFIES: player
            // EFFECTS: simulates a player's 'hit'
            @Override
            public void actionPerformed(ActionEvent e) {
                hit();
            }
        });

        standButton = new JButton(new AbstractAction("Stand") {
            // MODIFIES: dealer
            // EFFECTS: simulates a dealer's 'stand'
            @Override
            public void actionPerformed(ActionEvent e) {
                stand();
            }
        });

        hitButton.addActionListener(this);
        standButton.addActionListener(this);
        gamePlay();
        adding();
    }


    // MODIFIES: dealer
    // EFFECTS: simulates a dealer's 'stand'
    private void stand() {
        player.updateHandValue();

        dealerGUI.switchToAll();
        while (dealer.shouldHit()) {
            Card nextCard = deck.getRandCard();
            dealer.addCard(nextCard);
        }
        dealerGUI = new DealerCardsGUI(dealer);

        dealerGUI.exitFrame();


        result();

        exit();
    }

    // MODIFIES: player
    // EFFECTS: compares hand results from dealer and player
    private void result() {
        if (dealer.isBust()) {
            playerWin();
        } else if (dealer.getHandValue() > player.getHandValue()) {
            playerLose();
        } else if (player.getHandValue() > dealer.getHandValue()) {
            playerWin();
        } else {
            push(); 
        }
        try {
            writer = new Writer(ROUTE_TO_DATA);
            writer.open();
            writer.write(player);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    // EFFECTS: 10 second pause
    public void waitTen() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    // MODIFIES: player, dealer
    // EFFECTS: simulates a push
    private void push() {
        resultGUI = new ResultGUI("Tie!", player, this, dealerGUI, playerGui);
    }


    // MODIFIES: player
    // EFFECTS: simulates a player losing a hand
    private void playerLose() {
        String message = "Ahh, better luck next time";
        resultGUI = new ResultGUI(message, player, this, dealerGUI, playerGui);
        player.subFromBankroll(player.getCurrBetSize());
    }

    // MODIFIES: player
    // EFFECTS: gives player proper payout and starts another round
    private void playerWin() {
        resultGUI = new ResultGUI("Nice win!", player, this, dealerGUI, playerGui);
        player.addToBankroll(player.getCurrBetSize());
        try {
            /*
             * https://docs.oracle.com/javase/7/docs/api/javax/sound/midi/Synthesizer.html
             */
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] channels = midiSynth.getChannels();

            midiSynth.loadInstrument(instr[0]);


            channels[0].noteOn(60, 100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channels[0].noteOff(60);


        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }



    // MODIFIES: player
    // EFFECTS: simulates a player's 'hit'
    private void hit() {
        player.updateHandValue();

        if (playerGui != null) {
            playerGui.exit();
        }

        Card newCard = deck.getRandCard();
        player.addCard(newCard);

        playerGui = new PlayerCardsGUI(player);

    }

    // MODIFIES: this
    // EFFECTS: adds Swing stuff to each other
    private void adding() {
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(hitButton);
        panel.add(standButton);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: player, dealer
    // EFFECTS: starts the round
    private void gamePlay() {
        player.setHand(new ArrayList<Card>());
        dealer.setHand(new ArrayList<Card>());

        if (deck.getDeck().size() < SMALLEST_PLAYABLE_DECK_SIZE) {
            System.out.println("Sorry for the delay. We're adding cards to the shoe.");
            deck.addDeck();
        }

        player.addCard(deck.getRandCard());
        player.addCard(deck.getRandCard());
        dealer.addCard(deck.getRandCard());
        dealer.addCard(deck.getRandCard());

        playerGui = new PlayerCardsGUI(player);
        dealerGUI = new DealerCardsGUI(dealer);

    }

    // MODIFIES: this
    // EFFECTS: closes frame
    public void exit() {
        frame.dispose();
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

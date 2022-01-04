package ui;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Represents the ResultGUI tab
 */
public class ResultGUI extends JFrame implements ActionListener {
    private JLabel label;
    public static JFrame frame;
    private JPanel panel;
    private String message;
    private Player player;
    private JButton again;
    private PlayGUI pg;
    private DealerCardsGUI dg;
    private PlayerCardsGUI pcg;

    // Sets up ResultGUI
    public ResultGUI(String message, Player player, PlayGUI pg, DealerCardsGUI dg, PlayerCardsGUI pcg) {
        super("Blackjack UI");
        this.message = message;
        this.player = player;
        this.pg = pg;
        this.dg = dg;
        this.pcg = pcg;

        frame = new JFrame();
        panel = new JPanel();

        label = new JLabel(message);
        adding();
    }

    // MODIFIES: panel, frame
    // EFFECTS: sets up Swing elements
    private void adding() {
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        next();
    }

    // MODIFIES: pg, dg, pcg, panel, frame
    // EFFECTS: allows user to start the next round
    private void next() {

        again = new JButton(new AbstractAction("Play again") {
            @Override
            public void actionPerformed(ActionEvent e) {
                pg.exit();
                dg.exitFrame();
                dg.exitAllFrame();
                pcg.exit();
                exit();
                new SetBetSize(player);
            }
        });

        again.addActionListener(this);
        panel.add(again, BorderLayout.EAST);
        frame.add(panel);
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

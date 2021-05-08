package ui;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Represents GUI to set round's bet size
 */
public class SetBetSize extends JFrame implements ActionListener {
    private Player player;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JButton submitAmount;
    private JTextField amountField;

    // Sets up SetBetSize
    public SetBetSize(Player player) {
        this.player = player;
        System.out.println(player.getBankroll());

        frame = new JFrame();
        label = new JLabel("How much do you want to bet?");
        panel = new JPanel();
        amountField = new JTextField(20);
        submitAmount = new JButton();


        amountField.setBounds(10, 20, 80, 25);
        label.setBounds(10,10,50,15);
        submitAmount.setBounds(10, 40, 30, 25);
        submitAmount.setText("Bet!");
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(label);
        panel.add(amountField);
        panel.add(submitAmount);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        submitAmount.addActionListener(this);

    }


    // REQUIRES: amount is integer
    // MODIFIES: player
    // EFFECTS: sets player's currBetSize
    @Override
    public void actionPerformed(ActionEvent e) {
        int amount = Integer.parseInt(amountField.getText());
        player.setCurrBetSize(amount);
        frame.dispose();
        new PlayGUI(player);
    }
}

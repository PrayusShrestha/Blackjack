package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Represents the starting screen
 */
public class StartGUI extends JFrame implements ActionListener {
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JButton submitName;
    private JTextField nameField;

    // Sets up StartGUI
    public StartGUI() {
        super("Start");
        frame = new JFrame();
        label = new JLabel("What is your name?");
        panel = new JPanel();
        nameField = new JTextField(20);
        submitName = new JButton();

        nameField.setBounds(10, 20, 80, 25);
        label.setBounds(10,10,50,15);
        submitName.setBounds(10, 40, 30, 25);
        submitName.setText("Submit!");
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(label);
        panel.add(nameField);
        panel.add(submitName);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        submitName.addActionListener(this);
    }

    @Override
    // EFFECTS: initiates choice GUI with player's name passed
    public void actionPerformed(ActionEvent actionEvent) {
        String name = nameField.getText();
        frame.dispose();
        new ChoiceGUI(name);
    }
}

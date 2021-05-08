package model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/*
 *
 * Represents a player (end-user)
 * Has a name, bankroll, hand (and its value), and current player bet size
 *
*/
public class Player {
    
    private String name;
    private int bankroll;
    private int handValue;
    private ArrayList<Card> hand;
    private int currBetSize;

    // TODO: Change value of Ace (1 or 11) based on what would be best for the player

    // EFFECT: constructs a player with a name and bankroll amount
    public Player(String name, int amount) {
        this.name = name;
        this.bankroll = amount;
        this.hand = new ArrayList<Card>();
    }

    public String getName() {
        return this.name;
    }

    public int getBankroll() {
        return this.bankroll;
    }

    public int getHandValue() {
        return this.handValue;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public int getCurrBetSize() {
        return this.currBetSize;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setBankroll(int newAmount) {
        this.bankroll = newAmount;
    }

    public void setHandValue(int val) {
        this.handValue = val;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void setCurrBetSize(int betSize) {
        this.currBetSize = betSize;
    }

    // REQUIRES: int amount > 0
    // MODIFIES: this
    // EFFECTS: adds int amount to the player's bankroll and returns new bankroll
    public void addToBankroll(int amount) {
        this.bankroll += amount;
    }

    // REQUIRES: int amount > 0
    // MODIFIES: this
    // EFFECTS: subtracts int amount from bankroll
    public void subFromBankroll(int amount) {
        this.bankroll -= amount;
    }

    // REQUIRES: int val > 0
    // MODIFIES: this
    // EFFECTS: adds value of new card to current hand value
    public void addHandValue(int val) {
        this.handValue += val;
    }

    // MODIFIES: this
    // EFFECTS: adds Card c to the players hand
    public void addCard(Card c) {
        this.hand.add(c);
    }

    // REQUIRES: hand.size() > 0
    // MODIFIES: this
    // EFFECTS: sets hand value to the sum of all the cards
    public void updateHandValue() {
        handValue = 0;
        for (int i = 0; i < hand.size(); i++) {
            addHandValue(hand.get(i).getValue());
        }
    }

    // EFFECTS: checks if the player is out of money
    public boolean isOutOfMoney() {
        return this.bankroll <= 0;
    }

    // EFFECTS: checks if the player's card value's go over the limit
    public boolean isBust() {
        return this.handValue > 21;
    }


}

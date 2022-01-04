package model;

import java.util.ArrayList;

/*
 *
 * Represents the dealer (house)
 * Dealer will have a hand (Listof Card) and it's value
 *
 */
public class Dealer {

    private int handValue;
    private ArrayList<Card> hand;

    // TODO: Change value of Ace (1 or 11) based on what would be best for the dealer

    public Dealer() {
        hand = new ArrayList<Card>();
    }

    public int getHandValue() {
        return this.handValue;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

//    public void setHandValue(int handValue) {
//        this.handValue = handValue;
//    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    // MODIFIES: this
    // EFFECTS: gives dealer another card and updates hand value
    public void addCard(Card c) {
        this.hand.add(c);
        this.updateHandVal();
    }

    // EFFECTS: checks if dealer's hand is over 21
    public boolean isBust() {
        return this.handValue > 21;
    }

    // REQUIRES: int val > 0
    // MODIFIES: this
    // EFFECTS: adds int val to the dealer's current hand value
    public void addToHandValue(int val) {
        this.handValue += val;
    }

    // REQUIRES: hand.size > 0
    // MODIFIES: this
    // EFFECTS: keeps hand value up-to-date with hand
    public void updateHandVal() {
        this.handValue = 0;
        for (int i = 0; i < hand.size(); i++) {
            this.addToHandValue(hand.get(i).getValue());
        }
    }

    // EFFECTS: returns true if the dealer needs to hit
    public boolean shouldHit() {
        return this.handValue < 17;
    }
}

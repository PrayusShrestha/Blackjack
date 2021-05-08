package model;

import java.util.HashMap;
import java.util.Map;

import static model.CardName.*;

/*
 *
 * Represents a Card class
 * Card has a name, suit, value, and face
 *
 */
public class Card {

    private Suit suit;
    private int value;
    private CardName cardName;
    private static Map<CardName, Integer> pairings;

    public Card(Suit s, CardName cn) {
        this.pairings = new HashMap<CardName, Integer>();

        int counter = 1;
        for (CardName name : CardName.values()) {
            pairings.put(name, counter);
            counter++;
        }
        pairings.put(ACE, 11);
        pairings.put(JACK, 10);
        pairings.put(QUEEN, 10);
        pairings.put(KING, 10);

        this.suit = s;
        this.value = nameToVal(cn);
        this.cardName = cn;
    }

    public int getValue() {
        return this.value;
    }

    public CardName getName() {
        return cardName;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public void setCardName(CardName cn) {
        this.cardName = cn;
    }

    // EFFECTS: returns the blackjack value of the given card
    private int nameToVal(CardName cn) {
        return pairings.get(cn);
    }
}

package model;

import java.util.ArrayList;
import java.util.Random;

/*
 *
 * Represents the deck/shoe
 * Deck is able to select random cards and add refill
 *
 */

public class Deck {
    private ArrayList<Card> deck;

    // EFFECTS: Constructs a shoe
    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    // MODIFIES: this
    // EFFECTS: Adds a 52 card deck to the shoe
    public void addDeck() {
        for (Suit s : Suit.values()) {
            for (CardName cn : CardName.values()) {
                Card card = new Card(s, cn);
                card.setCardName(cn);
                deck.add(card);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Empties deck
    public void emptyDeck() {
        this.deck.clear();
    }

    // REQUIRES: deck.size() > 0
    // MODIFIES: this
    // EFFECTS: Pulls random card from remaining cards and also deletes it
    public Card getRandCard() {
        Random random = new Random();
        int idx = random.nextInt(this.deck.size());
        Card card = deck.get(idx);
        deck.remove(idx);
        return card;

    }
}

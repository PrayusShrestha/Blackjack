package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {
    Deck testDeck;

    @BeforeEach
    public void setup() {
        testDeck = new Deck();
    }

    @Test
    public void testAddDeck() {
        testDeck.emptyDeck();
        testDeck.addDeck();
        assertEquals(52, testDeck.getDeck().size());
    }

    @Test
    public void testGetRandCard() {
        testDeck.addDeck();
        testDeck.getRandCard();
        assertEquals(51, testDeck.getDeck().size());
    }

    @Test
    public void testClearDeck() {
        testDeck.addDeck();
        testDeck.emptyDeck();
        assertEquals(0, testDeck.getDeck().size());
    }

}

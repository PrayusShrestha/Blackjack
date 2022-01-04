package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {
    Dealer dealer;

    @BeforeEach
    public void setup() {
        dealer = new Dealer();
    }


    @Test
    public void testHitOnce() {
        Card card = new Card(Suit.SPADES, CardName.ACE);
        dealer.addCard(card);
        assertEquals(1, dealer.getHand().size());
        assertEquals(11, dealer.getHand().get(0).getValue());
    }

    @Test
    public void testHitTwice() {
        Card c1 = new Card(Suit.SPADES, CardName.ACE);
        Card c2 = new Card(Suit.DIAMONDS, CardName.KING);
        dealer.addCard(c1);
        dealer.addCard(c2);

        assertEquals(2, dealer.getHand().size());
    }

    @Test
    public void testIsBustTrue() {
        Card c1 = new Card(Suit.SPADES, CardName.TEN);
        Card c2 = new Card(Suit.DIAMONDS, CardName.EIGHT);
        Card c3 = new Card(Suit.DIAMONDS, CardName.NINE);
        dealer.addCard(c1);
        dealer.addCard(c2);
        dealer.addCard(c3);

        assertTrue(dealer.isBust());
    }

    @Test
    public void testIsBustFalse() {
        Card c1 = new Card(Suit.SPADES, CardName.TEN);
        Card c2 = new Card(Suit.DIAMONDS, CardName.EIGHT);
        dealer.addCard(c1);
        dealer.addCard(c2);

        assertFalse(dealer.isBust());
    }


    @Test
    public void testShouldHitTrue() {
        Card c1 = new Card(Suit.SPADES, CardName.THREE);
        Card c2 = new Card(Suit.DIAMONDS, CardName.EIGHT);
        dealer.addCard(c1);
        dealer.addCard(c2);

        assertEquals(11, dealer.getHandValue());
        assertTrue(dealer.shouldHit());
    }

    @Test
    public void testShouldHitFalse() {
        Card c1 = new Card(Suit.SPADES, CardName.TEN);
        Card c2 = new Card(Suit.DIAMONDS, CardName.KING);
        dealer.addCard(c1);
        dealer.addCard(c2);

        assertEquals(20, dealer.getHandValue());
        assertFalse(dealer.shouldHit());
    }
    @Test
    public void testSetHandEmptyStart() {
        Card c1 = new Card(Suit.SPADES, CardName.NINE);
        Card c2 = new Card(Suit.DIAMONDS, CardName.KING);

        ArrayList<Card> testHand = new ArrayList<Card>();
        testHand.add(c1);
        testHand.add(c2);
        dealer.setHand(testHand);
        dealer.updateHandVal();

        assertEquals(testHand, dealer.getHand());
        assertEquals(19, dealer.getHandValue());
        assertEquals(2, dealer.getHand().size());
    }

}

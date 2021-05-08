package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {

    Player testPlayer;

    @BeforeEach
    public void setup() {
        testPlayer = new Player("Prayus", 100);
    }

    @Test
    public void testAddToBankrollOnce() {
        int amount = 50;
        testPlayer.addToBankroll(amount);
        assertEquals(150, testPlayer.getBankroll());
    }

    @Test
    public void testAddToBankrollMupltiple() {
        for(int i = 1; i <= 10; i++) {
            testPlayer.addToBankroll(i);
        }
        assertEquals(155, testPlayer.getBankroll());
    }

    @Test
    public void testSubFromBankrollOnce() {
        testPlayer.subFromBankroll(50);
        assertEquals(50, testPlayer.getBankroll());
    }

    @Test
    public void testSubFromBankrollMultiple() {
        for(int i = 5; i > 0; i--) {
            testPlayer.subFromBankroll(i);
        }
        assertEquals(85, testPlayer.getBankroll());
    }

    @Test
    public void testAddToHandValueOnce() {
        testPlayer.setHandValue(11);
        testPlayer.addHandValue(10);
        assertEquals(21, testPlayer.getHandValue());
    }

    @Test
    public void testAddToHandValueTwice() {
        testPlayer.setHandValue(5);
        for(int i = 5; i < 7; i++) {
            testPlayer.addHandValue(i);
        }
        assertEquals(16, testPlayer.getHandValue());
    }

    @Test
    public void testIsOutOfMoneyTrue() {
        testPlayer.setBankroll(0);
        assertTrue(testPlayer.isOutOfMoney());
    }

    @Test
    public void testIsOutOfMoneyFalse() {
        testPlayer.subFromBankroll(50);
        assertFalse(testPlayer.isOutOfMoney());
    }

    @Test
    public void testAddACard() {
        Card test = new Card(Suit.DIAMONDS, CardName.KING);
        testPlayer.addCard(test);
        ArrayList<Card> hand = testPlayer.getHand();
        assertEquals(1, hand.size());
    }

    @Test
    public void testAddMultipleCards() {
        ArrayList<Card> test = new ArrayList<Card>();
        Card c1 = new Card(Suit.CLUBS, CardName.EIGHT);
        Card c2 = new Card(Suit.HEARTS, CardName.QUEEN);
        Card c3 = new Card(Suit.SPADES, CardName.ACE);
        test.add(c1);
        test.add(c2);
        test.add(c3);

        for(Card c : test) {
            testPlayer.addCard(c);
        }
        assertEquals(3, testPlayer.getHand().size());
    }

    @Test
    public void testSetHandThreeCards() {
        ArrayList<Card> test = new ArrayList<Card>();
        Card c1 = new Card(Suit.CLUBS, CardName.EIGHT);
        Card c2 = new Card(Suit.HEARTS, CardName.QUEEN);
        Card c3 = new Card(Suit.SPADES, CardName.ACE);
        test.add(c1);
        test.add(c2);
        test.add(c3);

        testPlayer.setHand(test);
        assertEquals(3, testPlayer.getHand().size());
    }


    @Test
    public void testUpdateHandValueNoCards() {
        ArrayList<Card> test = new ArrayList<Card>();
        testPlayer.setHand(test);
        testPlayer.updateHandValue();

        assertEquals(0, testPlayer.getHandValue());
    }

    @Test
    public void testUpdateHandValueMultipleCards() {
        ArrayList<Card> test = new ArrayList<Card>();
        Card c1 = new Card(Suit.CLUBS, CardName.TWO);
        Card c2 = new Card(Suit.HEARTS, CardName.QUEEN);
        Card c3 = new Card(Suit.SPADES, CardName.SEVEN);
        test.add(c1);
        test.add(c2);
        test.add(c3);
        testPlayer.setHand(test);
        testPlayer.updateHandValue();
        assertEquals(19, testPlayer.getHandValue());
    }

    @Test
    public void testIsBustTrue() {
        testPlayer.setHandValue(25);
        assertTrue(testPlayer.isBust());
    }

    @Test
    public void testIsBustFalse() {
        testPlayer.setHandValue(20);
        assertFalse(testPlayer.isBust());
    }

    @Test
    public void testSetName() {
        testPlayer.setName("Bill Gates");
        assertEquals("Bill Gates", testPlayer.getName());
    }

    @Test
    public void testSetBetSizeOldZero() {
        testPlayer.setCurrBetSize(100);
        assertEquals(100, testPlayer.getCurrBetSize());
    }

    @Test
    public void testSetBetSizeOldFifty() {
        testPlayer.setCurrBetSize(50);
        testPlayer.setCurrBetSize(25);
        assertEquals(25, testPlayer.getCurrBetSize());
    }

}

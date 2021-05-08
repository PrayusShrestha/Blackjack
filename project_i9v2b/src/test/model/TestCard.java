package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCard {
    Card testCard;


    @BeforeEach
    public void setup() {
        testCard = new Card(Suit.DIAMONDS, CardName.KING);
    }

    @Test
    public void testNameToVal() {
        assertEquals(CardName.KING, testCard.getName());
        assertEquals(Suit.DIAMONDS, testCard.getSuit());
        assertEquals(10, testCard.getValue());
    }


}

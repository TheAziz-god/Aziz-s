import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TestGame {

    @Test
    public void testCardComparisonHigher() {
        Card low = new Card(1, 0);
        Card high = new Card(10, 1);
        assertTrue(high.compareTo(low) > 0);
    }

    @Test
    public void testCardComparisonEqual() {
        Card c1 = new Card(5, 0);
        Card c2 = new Card(5, 2);
        assertEquals(0, c1.compareTo(c2));
    }

    @Test
    public void testToString() {
        Card card = new Card(12, 3);
        assertEquals("Ace of Spades", card.toString());
    }

    @Test
    public void testAddPoints() {
        Player p = new Player("Test");
        p.addPoints(5);
        assertEquals(5, p.getScore());
    }

    @Test
    public void testAddCard() {
        Player p = new Player("Test");
        p.addCard(new Card(2, 1));
        assertEquals(1, p.getCards().size());
    }

    @Test
    public void testPlayerName() {
        Player p = new Player("John");
        assertEquals("John", p.getName());
    }

    @Test
    public void testDeckDeal() {
        Deck deck = new Deck();
        int before = deck.size();
        deck.deal();
        assertEquals(before - 1, deck.size());
    }

    @Test
    public void testReturnCard() {
        Deck deck = new Deck();
        int before = deck.size();
        deck.returnCard(new Card(3, 1));
        assertEquals(before + 1, deck.size());
    }

    @Test
    public void testLongestSequence() {
        Player p = new Player("Test");
        p.addCard(new Card(1, 0));
        p.addCard(new Card(2, 1));
        p.addCard(new Card(3, 2));
        assertEquals(3, p.getLongestSequence());
    }

    @Test
    public void testHighestSuitCount() {
        Player p = new Player("Test");
        p.addCard(new Card(1, 0));
        p.addCard(new Card(5, 0));
        p.addCard(new Card(9, 0));
        assertEquals(3, p.getHighestSuitCount());
    }

    @Test
    public void testTiePoints() {
        Player p1 = new Player("A");
        Player p2 = new Player("B");

        Card c1 = new Card(10, 0);
        Card c2 = new Card(10, 1);

        if (c1.compareTo(c2) == 0) {
            p1.addPoints(1);
            p2.addPoints(1);
        }

        assertEquals(1, p1.getScore());
        assertEquals(1, p2.getScore());
    }

    @Test
    public void testWinnerPoints() {
        Player p = new Player("A");

        Card high = new Card(12, 0);
        Card low = new Card(5, 1);

        if (high.compareTo(low) > 0) {
            p.addPoints(3);
        }

        assertEquals(3, p.getScore());
    }
}
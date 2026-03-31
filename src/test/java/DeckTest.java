import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    void testDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.size());
    }

    @Test
    void testDealCard() {
        Deck deck = new Deck();
        Card card = deck.deal();

        assertNotNull(card);
        assertEquals(51, deck.size());
    }

    @Test
    void testContainsEquivalentCard() {
        Deck deck = new Deck();
        Card knownCard = new Card(0, 0);

        assertTrue(deck.contains(knownCard));
    }
}

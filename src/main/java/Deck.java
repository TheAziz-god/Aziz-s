import java.util.*;
public class Deck {
    private final ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        for (int i = 0; i < 52; i++) {
            deck.add(new Card(i % 13, i / 13));
        }
        Collections.shuffle(deck);
    }

    public Card deal() {
        if (!deck.isEmpty()) {
            Card c = deck.get(0);
            deck.remove(0);
            return c;
        } else {
            return null;
        }
    }

    public void returnCard(Card c) {
        if (c != null) {
            deck.add(c);
        }
    }

    public int size() {
        return deck.size();
    }

    public boolean contains(Card card) {
        return deck.contains(card);
    }
}


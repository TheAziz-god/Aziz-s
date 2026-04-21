import java.util.*;

public class Deck {

    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 0; rank < 13; rank++) {
                cards.add(new Card(rank, suit));
            }
        }

        Collections.shuffle(cards);
    }

    public Card deal() {
        if (cards.isEmpty()) return null;
        return cards.remove(0);
    }

    public void returnCard(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }
}
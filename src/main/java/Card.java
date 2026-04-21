public class Card implements Comparable<Card> {

    private int rank; // 0–12
    private int suit; // 0–3

    private static final String[] ranks = {
            "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "Jack", "Queen", "King", "Ace"
    };

    private static final String[] suits = {
            "Clubs", "Diamonds", "Hearts", "Spades"
    };

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRankValue() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.rank, other.rank);
    }

    @Override
    public String toString() {
        return ranks[rank] + " of " + suits[suit];
    }
}
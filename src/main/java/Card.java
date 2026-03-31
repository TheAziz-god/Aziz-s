import java.util.Random;
import java.util.Objects;

public class Card implements Comparable<Card> {
    private final int rank; // 0..12
    private final int suit; // 0..3

    private static final String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "Jack", "Queen", "King", "Ace"
    };

    private static final String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"
    };

    public Card() {
        Random random = new Random();
        this.rank = random.nextInt(RANKS.length);
        this.suit = random.nextInt(SUITS.length);
    }

    public Card(int rank, int suit) {
        if (rank < 0 || rank >= RANKS.length) {
            throw new IllegalArgumentException("Rank must be between 0 and 12");
        }
        if (suit < 0 || suit >= SUITS.length) {
            throw new IllegalArgumentException("Suit must be between 0 and 3");
        }
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return RANKS[rank];
    }

    public String getSuit() {
        return SUITS[suit];
    }

    public int getRankValue() {
        return rank;
    }

    public boolean isBiggerThan(Card anotherCard) {
        return this.rank > anotherCard.rank;
    }

    @Override
    public int compareTo(Card otherCard) {
        if (this.getRankValue() > otherCard.getRankValue()) {
            return 1;
        } else if (this.getRankValue() < otherCard.getRankValue()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return getRank() + " of " + getSuit();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Card other)) {
            return false;
        }
        return rank == other.rank && suit == other.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}

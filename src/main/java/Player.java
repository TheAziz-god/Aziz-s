import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private int score;
    private final List<Card> collectedCards;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.collectedCards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        score += points;
    }

    public void addCard(Card card) {
        collectedCards.add(card);
    }

    public List<Card> getCollectedCards() {
        return collectedCards;
    }

    public void showCards() {
        if (collectedCards.isEmpty()) {
            System.out.println(name + " collected no cards.");
            return;
        }

        System.out.println(name + " collected cards:");
        for (Card c : collectedCards) {
            System.out.println(" - " + c);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
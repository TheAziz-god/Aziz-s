import java.util.ArrayList;

public class Player {

    private final String name;
    private int score;
    private final ArrayList<Card> collectedCards;

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

    public ArrayList<Card> getCollectedCards() {
        return collectedCards;
    }

    public void showCards() {
        System.out.println(name + " cards:");
        for (Card c : collectedCards) {
            System.out.println(c);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

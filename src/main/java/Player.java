import java.util.*;

public class Player {

    private String name;
    private int score;
    private List<Card> collectedCards;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.collectedCards = new ArrayList<>();
    }

    public String getName() { return name; }
    public int getScore() { return score; }
    public List<Card> getCards() { return collectedCards; }

    public void addPoints(int points) { score += points; }
    public void addCard(Card card) { collectedCards.add(card); }

    public void showCards() {
        System.out.println(name + " cards: " + collectedCards);
    }

    private int readInt(Scanner input) {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.print("Invalid input. Try again: ");
            }
        }
    }

    public void takeTurn(Scanner input, Deck deck) {

        if (name.equalsIgnoreCase("Computer")) {

            Random rand = new Random();
            int num = rand.nextInt(3);

            System.out.println("\n--- Computer's turn ---");
            showCards();

            System.out.println("Computer chooses to replace " + num + " card(s).");

            for (int i = 0; i < num; i++) {
                if (collectedCards.isEmpty()) break;

                int index = rand.nextInt(collectedCards.size());

                Card removed = collectedCards.remove(index);
                deck.returnCard(removed);

                System.out.println("Computer removed: " + removed);

                Card newCard = deck.deal();
                collectedCards.add(newCard);

                System.out.println("Computer drew: " + newCard);
            }

            System.out.println("Computer updated cards:");
            showCards();
            return;
        }


        System.out.println("\n--- " + name + "'s turn ---");

        if (collectedCards.isEmpty()) {
            System.out.println("No cards to replace.");
            return;
        }

        showCards();

        int num;
        while (true) {
            System.out.print("Replace 0, 1 or 2 cards: ");
            num = readInt(input);

            if (num == 0 || num == 1 || num == 2) break;
            System.out.println("Enter only 0, 1 or 2.");
        }

        if (num == 0) {
            System.out.println("No cards replaced.");
            return;
        }

        for (int i = 0; i < num; i++) {

            showCards();

            System.out.print("Enter index to remove (0 to " + (collectedCards.size() - 1) + "): ");
            int index = readInt(input);

            if (index >= 0 && index < collectedCards.size()) {

                Card removed = collectedCards.remove(index);
                deck.returnCard(removed);

                System.out.println("Removed: " + removed);

                Card newCard = deck.deal();
                collectedCards.add(newCard);

                System.out.println("New card: " + newCard);

            } else {
                System.out.println("Invalid index.");
                i--;
            }
        }

        System.out.println("Updated cards:");
        showCards();
    }

    public int getLongestSequence() {
        if (collectedCards.isEmpty()) return 0;

        List<Integer> ranks = new ArrayList<>();
        for (Card c : collectedCards) ranks.add(c.getRankValue());

        Collections.sort(ranks);

        int longest = 1, current = 1;

        for (int i = 1; i < ranks.size(); i++) {
            if (ranks.get(i) == ranks.get(i - 1) + 1) {
                current++;
                longest = Math.max(longest, current);
            } else {
                current = 1;
            }
        }

        return longest;
    }

    public int getHighestSuitCount() {
        int[] count = new int[4];
        for (Card c : collectedCards) count[c.getSuit()]++;

        int max = 0;
        for (int c : count) max = Math.max(max, c);

        return max;
    }
}
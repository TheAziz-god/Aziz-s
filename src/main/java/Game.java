import java.util.*;

public class Game {

    private List<Player> players;
    private Deck deck;
    private int rounds;
    private Scanner input;

    public Game() {
        players = new ArrayList<>();
        deck = new Deck();
        input = new Scanner(System.in);
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.print("Invalid input. Try again: ");
            }
        }
    }

    private void waitForEnter() {
        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    public void setupGame() {

        System.out.print("Enter number of players (2-5): ");
        int numPlayers = readInt();

        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            players.add(new Player(input.nextLine()));
        }

        System.out.print("Enter number of rounds (5-10): ");
        rounds = readInt();

        playGame();
    }

    public void playGame() {

        for (int r = 1; r <= rounds; r++) {

            System.out.println("\n===== ROUND " + r + " =====");

            Map<Player, Card> roundCards = new HashMap<>();

            for (Player p : players) {
                Card c = deck.deal();
                roundCards.put(p, c);
                System.out.println(p.getName() + " -> " + c);
            }

            List<Player> winners = new ArrayList<>();
            Card highest = null;

            for (Player p : players) {
                Card c = roundCards.get(p);

                if (highest == null || c.compareTo(highest) > 0) {
                    highest = c;
                    winners.clear();
                    winners.add(p);
                } else if (c.compareTo(highest) == 0) {
                    winners.add(p);
                }
            }

            if (winners.size() > 1) {
                System.out.println("Tie between:");
                for (Player p : winners) {
                    p.addPoints(1);
                    p.addCard(roundCards.get(p));
                    System.out.println(p.getName());
                }
            } else {
                Player winner = winners.get(0);
                winner.addPoints(3);
                winner.addCard(highest);
                System.out.println("Winner: " + winner.getName());
            }

            System.out.println("\nScores:");
            for (Player p : players) {
                System.out.println(p.getName() + ": " + p.getScore());
            }

            System.out.println("\nPlayer Cards:");
            for (Player p : players) {
                p.showCards();
            }

            waitForEnter();
        }

        optionalAdjustmentStage();
        showFinalResults();
    }

    private void optionalAdjustmentStage() {

        System.out.println("\n=== REPLACEMENT STAGE ===");

        for (Player p : players) {
            p.takeTurn(input, deck);
        }

        System.out.println("\nUpdated Cards:");
        for (Player p : players) {
            p.showCards();
        }
    }

    private void showFinalResults() {

        System.out.println("\n=== FINAL CARDS ===");
        for (Player p : players) {
            p.showCards();
        }

        int maxSeq = players.stream().mapToInt(Player::getLongestSequence).max().orElse(0);
        long seqWinners = players.stream().filter(p -> p.getLongestSequence() == maxSeq).count();

        System.out.println("\n=== LONGEST SEQUENCE BONUS ===");
        for (Player p : players) {
            int seq = p.getLongestSequence();
            int bonus = (seq == maxSeq) ? (seqWinners > 1 ? 2 : 5) : 0;
            p.addPoints(bonus);
            System.out.println(p.getName() + ": seq=" + seq + " bonus=" + bonus);
        }

        int maxSuit = players.stream().mapToInt(Player::getHighestSuitCount).max().orElse(0);
        long suitWinners = players.stream().filter(p -> p.getHighestSuitCount() == maxSuit).count();

        System.out.println("\n=== SUIT COUNT BONUS ===");
        for (Player p : players) {
            int suit = p.getHighestSuitCount();
            int bonus = (suit == maxSuit) ? (suitWinners > 1 ? 2 : 5) : 0;
            p.addPoints(bonus);
            System.out.println(p.getName() + ": suit=" + suit + " bonus=" + bonus);
        }

        players.sort((a, b) -> b.getScore() - a.getScore());

        System.out.println("\n=== FINAL SCORES ===");
        for (Player p : players) {
            System.out.println(p.getName() + ": " + p.getScore());
        }

        int topScore = players.get(0).getScore();

        List<Player> winners = new ArrayList<>();
        for (Player p : players) {
            if (p.getScore() == topScore) {
                winners.add(p);
            }
        }

        if (winners.size() > 1) {
            System.out.println("\nGame is a DRAW between:");
            for (Player p : winners) {
                System.out.println(p.getName());
            }
        } else {
            System.out.println("\nWinner: " + winners.get(0).getName());
        }
    }
}
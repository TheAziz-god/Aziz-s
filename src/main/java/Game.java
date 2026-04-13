import java.util.*;

public class Game {

    private final List<Player> players;
    private final Deck deck;
    private final int rounds;

    public Game(List<Player> players, int rounds) {
        this.players = players;
        this.rounds = rounds;
        this.deck = new Deck();
    }

    public void playGame() {

        for (int r = 1; r <= rounds; r++) {

            System.out.println("\n----- Round " + r + " -----");

            Map<Player, Card> roundCards = new HashMap<>();

            for (Player player : players) {

                Card card = deck.deal();

                if (card == null) {
                    System.out.println("Deck is empty.");
                    return;
                }

                roundCards.put(player, card);

                System.out.println(player.getName() + " drew " + card);
            }

            Card highest = Collections.max(roundCards.values());

            List<Player> winners = new ArrayList<>();

            for (Map.Entry<Player, Card> entry : roundCards.entrySet()) {

                if (entry.getValue().compareTo(highest) == 0) {
                    winners.add(entry.getKey());
                }
            }

            if (winners.size() == 1) {

                Player winner = winners.get(0);
                winner.addPoints(3);
                winner.addCard(roundCards.get(winner));

                System.out.println("Winner: " + winner.getName());

            } else {

                System.out.println("Tie between players:");

                for (Player p : winners) {
                    System.out.println(p.getName());
                    p.addPoints(1);
                    p.addCard(roundCards.get(p));
                }
            }

            for (Map.Entry<Player, Card> entry : roundCards.entrySet()) {

                if (!winners.contains(entry.getKey())) {
                    deck.returnCard(entry.getValue());
                }
            }

            showScores();
        }
    }

    private void showScores() {

        System.out.println("\nCurrent Scores:");

        for (Player p : players) {
            System.out.println(p.getName() + " : " + p.getScore());
        }
    }

    public void showCollectedCards() {

        System.out.println("\nCollected Cards:");

        for (Player p : players) {
            p.showCards();
        }
    }

    public void showWinner() {

        players.sort((a, b) -> b.getScore() - a.getScore());

        System.out.println("\nFinal Scores:");

        int highest = players.get(0).getScore();

        for (Player p : players) {
            System.out.println(p.getName() + " : " + p.getScore());
        }

        List<Player> winners = new ArrayList<>();

        for (Player p : players) {

            if (p.getScore() == highest) {
                winners.add(p);
            }
        }

        if (winners.size() == 1) {

            System.out.println("\nWinner: " + winners.get(0).getName());

        } else {

            System.out.println("\nGame ended in a draw between:");

            for (Player p : winners) {
                System.out.println(p.getName());
            }
        }
    }
}
import java.util.*;

public class Game {

    private ArrayList<Player> players;
    private Deck deck;
    private int rounds;

    public Game(ArrayList<Player> players, int rounds) {
        this.players = players;
        this.rounds = rounds;
        this.deck = new Deck();
    }

    public void playGame() {

        for (int r = 1; r <= rounds; r++) {
            if (deck.size() < players.size()) {
                System.out.println("Not enough cards left to continue the game.");
                break;
            }

            System.out.println("\nRound " + r);

            HashMap<Player, Card> roundCards = new HashMap<>();

            for (Player p : players) {
                Card c = deck.deal();
                if (c == null) {
                    System.out.println("Deck is empty.");
                    return;
                }
                roundCards.put(p, c);
                System.out.println(p.getName() + " drew " + c);
            }

            Card highest = Collections.max(roundCards.values());

            ArrayList<Player> winners = new ArrayList<>();

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

                System.out.println("Tie!");

                for (Player p : winners) {
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
        System.out.println("\nScores:");
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
        int highestScore = players.get(0).getScore();

        System.out.println("\nFinal Scores:");

        for (Player p : players) {
            System.out.println(p.getName() + " : " + p.getScore());
        }

        ArrayList<Player> winners = new ArrayList<>();
        for (Player player : players) {
            if (player.getScore() == highestScore) {
                winners.add(player);
            }
        }

        if (winners.size() == 1) {
            System.out.println("Winner: " + winners.get(0).getName());
        } else {
            System.out.println("Winners: " + winners);
        }
    }
}

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numberOfPlayers = readNumberInRange(scanner, "Enter number of players (2-5): ", 2, 5);

        List<Player> players = new ArrayList<>();

        for (int i = 1; i <= numberOfPlayers; i++) {

            String name = readNonEmptyLine(scanner, "Enter name for player " + i + ": ");

            players.add(new Player(name));
        }

        int rounds = readNumberInRange(scanner, "Enter number of rounds (5-10): ", 5, 10);

        Game game = new Game(players, rounds);

        game.playGame();

        game.showCollectedCards();

        game.showWinner();
    }

    private static int readNumberInRange(Scanner scanner, String prompt, int min, int max) {

        while (true) {

            System.out.print(prompt);

            if (scanner.hasNextInt()) {

                int value = scanner.nextInt();
                scanner.nextLine();

                if (value >= min && value <= max) {
                    return value;
                }
            } else {

                scanner.nextLine();
            }

            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    private static String readNonEmptyLine(Scanner scanner, String prompt) {

        while (true) {

            System.out.print(prompt);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Name cannot be empty.");
        }
    }
}
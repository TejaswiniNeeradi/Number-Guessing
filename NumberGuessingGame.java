import java.util.*;

public class NumberGuessingGame {
    private List<Integer> scoreBoard = new ArrayList<>();

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.start();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        playGame(scanner);
                        break;
                    case 2:
                        displayScoreBoard();
                        break;
                    case 3:
                        System.out.println("Thanks for playing! Goodbye.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
        scanner.close();
    }

    private void showMenu() {
        System.out.println("--------------------");
        System.out.println("Welcome to the Number Guessing Game");
        System.out.println("1) Play the Game");
        System.out.println("2) View Scoreboard");
        System.out.println("3) Exit");
        System.out.println("--------------------");
        System.out.print("Please select an option: ");
    }

    private void playGame(Scanner scanner) {
        System.out.print("Enter the range for the numbers (e.g., 100 for 1-100): ");
        int numberRange = scanner.nextInt();
        int targetNumber = generateRandomNumber(numberRange);
        int attempts = getUserGuess(scanner, targetNumber);

        scoreBoard.add(attempts);
        System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
    }

    private int generateRandomNumber(int range) {
        Random random = new Random();
        return random.nextInt(range) + 1;
    }

    private int getUserGuess(Scanner scanner, int targetNumber) {
        int guess;
        int attempts = 0;

        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;

            if (guess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
            }
        } while (guess != targetNumber);

        return attempts;
    }

    private void displayScoreBoard() {
        System.out.println("--------------------");
        System.out.println("Scoreboard");
        System.out.println("--------------------");

        if (scoreBoard.isEmpty()) {
            System.out.println("No scores available. Play a game to see scores.");
            return;
        }

        Collections.sort(scoreBoard);
        System.out.println("Your attempts for each game (sorted by fastest):");
        for (int attempts : scoreBoard) {
            System.out.println(attempts + " attempts");
        }
    }
}

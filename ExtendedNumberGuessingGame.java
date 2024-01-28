import java.util.Random;
import java.util.Scanner;

public class ExtendedNumberGuessingGame {

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 100;
        int generatedNumber;
        int maxAttempts = 10; // Limit the number of attempts
        int rounds = 0;
        int totalAttempts = 0;
        boolean playAgain = true;

        System.out.println("Welcome to Extended Guess the Number Game!");

        while (playAgain) {
            generatedNumber = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("\nRound " + (rounds + 1) + ": Guess the number between " + min + " and " + max);

            while (!hasGuessedCorrectly && attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                totalAttempts++;

                if (userGuess == generatedNumber) {
                    hasGuessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                } else if (userGuess > generatedNumber) {
                    System.out.println("Try again! Your guess is too high.");
                } else {
                    System.out.println("Try again! Your guess is too low.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + generatedNumber);
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            playAgain = playAgainResponse.equals("yes");
            rounds++;
        }

        System.out.println("\nGame Over! Your total score: " + calculateScore(rounds, totalAttempts));
        scanner.close();
    }

    private static int calculateScore(int rounds, int totalAttempts) {
        // You can define your scoring mechanism here, for example, based on rounds won or attempts taken.
        // For simplicity, let's use the total number of attempts as the score.
        return totalAttempts;
    }
}

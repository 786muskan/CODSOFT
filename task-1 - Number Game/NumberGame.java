import javax.swing.*;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        try {
            Random random = new Random();
            int totalScore = 0;
            boolean playAgain = true;

            while (playAgain) {
                int numberToGuess = random.nextInt(100) + 1; // 1 to 100
                int attemptsLeft = 7;
                int attemptsTaken = 0;
                boolean guessedCorrectly = false;

                JOptionPane.showMessageDialog(null,
                        "Welcome to the Number Guessing Game!\nI'm thinking of a number between 1 and 100.\nYou have " + attemptsLeft + " attempts to guess it.");

                while (attemptsLeft > 0) {
                    String input = JOptionPane.showInputDialog("Enter your guess (Attempt " + (attemptsTaken + 1) + "):");

                    if (input == null) {
                        JOptionPane.showMessageDialog(null, "Game cancelled.");
                        return;
                    }

                    int userGuess;
                    try {
                        userGuess = Integer.parseInt(input);
                        if (userGuess < 1 || userGuess > 100) {
                            JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 100.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
                        continue;
                    }

                    attemptsTaken++;

                    if (userGuess == numberToGuess) {
                        JOptionPane.showMessageDialog(null,
                                "Correct! You guessed the number in " + attemptsTaken + " attempts.");
                        guessedCorrectly = true;
                        totalScore += (10 - attemptsTaken); // Score logic
                        break;
                    } else if (userGuess < numberToGuess) {
                        JOptionPane.showMessageDialog(null, "Too low!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Too high!");
                    }

                    attemptsLeft--;

                    if (attemptsLeft == 0 && !guessedCorrectly) {
                        JOptionPane.showMessageDialog(null, "You're out of attempts! The correct number was " + numberToGuess + ".");
                    }
                }

                JOptionPane.showMessageDialog(null, "Your current score: " + totalScore);

                int option = JOptionPane.showConfirmDialog(null, "Do you want to play another round?", "Play Again",
                        JOptionPane.YES_NO_OPTION);

                playAgain = (option == JOptionPane.YES_OPTION);
            }

            JOptionPane.showMessageDialog(null, "Thanks for playing! Your final score is: " + totalScore);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

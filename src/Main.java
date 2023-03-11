import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!");
        System.out.println("Type \"exit\" at any time to exit the game");
        System.out.println("Guesses should be entered as a single character");
        boolean doPlay = true;
        Scanner scanner = new Scanner(System.in);
        WordMap setup = new WordMap();
        while(doPlay) {
            //Setup
            String word = setup.getWord();
            GameRound game = new GameRound(word);
            System.out.println(game.toString());
            // This is the value that will allow me to terminate the gameloop, either because the user
            // guessed the word, or because they wanted to exit the game
            boolean isRoundRunning = true;
            while(isRoundRunning) {
                System.out.println("Enter your next guess");

                String input = scanner.nextLine();
                input.toLowerCase();

                // Time to parse scanner inputs
                if(input.length() > 1) {
                    if (input.compareTo("exit") == 0) {
                        return;
                    }
                    else {
                        System.out.println("Please enter a valid input. Valid input is either a single letter, or" +
                                " \"exit\" if you wish to exit the game");
                    }
                // This parses single character inputs
                } else {
                    Character guess = input.charAt(0);
                    // This if statement makes sure that the input is a valid character
                    if(guess < 'a' || guess > 'z') {
                        System.out.println("Please enter a valid input. Valid input is either a single letter, or" +
                                " \"exit\" if you wish to exit the game");
                    } else if(game.guessLetter(guess)) {
                        isRoundRunning = false;
                    }
                }
                System.out.println(game.toString());
            }
            System.out.println("Good Job! You correctly guessed the word " + word + "!");
            System.out.println("You took " + game.getTotalGuesses() + " guesses, with " + game.getCorrectGuesses()
                    + " correct guesses and " + game.getWrongGuesses() + " incorrect guesses!");
            // The next section asks if they want to play again, and then parses through three options:
            // 1. If they say "yes"
            // 2. If they say "no" or "exit"
            // 3. If they enter an invalid input
            System.out.println("Would you like to play again? Type \"yes\" to play again or \"no\" to exit");
            boolean arefinished = true;
            while(arefinished) {
                String input = scanner.nextLine();
                input.toLowerCase();
                // Section 1: "yes"
                if (input.compareTo("yes") == 0) {
                    System.out.println("Initializing new game");
                    //This is set to false simply to exit the loop
                    arefinished = false;
                // Section 2: "no" or "exit"
                } else if (input.compareTo("no") == 0 || input.compareTo("exit") == 0) {
                    System.out.println("Thank you for playing, have a great day!");
                    return;
                // Section 3: invalid input
                } else {
                    System.out.println("Please enter a valid input. Type \"yes\" to continue or \"no\" to exit the game");
                }
            }
        }
    }
}

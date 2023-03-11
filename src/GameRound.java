public class GameRound {

    String word;
    StringBuilder wordProgress = new StringBuilder();
    StringBuilder guessedLetters = new StringBuilder();

    int totalGuesses = 0;
    int correctGuesses = 0;
    int wrongGuesses = 0;
    int spacesLeft = -1;

    GameRound(String word) {
        this.word = word;
        // Initialize wordProgress, the display word
        for(int i = 0; i < word.length(); ++i) {
            wordProgress.append('_');
        }
        spacesLeft = word.length();
    }

    private void changeOutput(int index, Character letter) {
        wordProgress.setCharAt(index, letter);
                --spacesLeft;
    }

    public Boolean guessLetter(Character letter) {
        // First check to make sure this is not a duplicate guess
        if(wordProgress.toString().indexOf(letter) != -1 || guessedLetters.toString().indexOf(letter) != -1) {
            System.out.println("You have already guessed this letter, please guess a new letter");
            return false; //Since these are duplicate letters, I can return without checking anything else
        }
        if(word.indexOf(letter) != -1) {
            ++correctGuesses;
            ++totalGuesses;
            int index = 0;
            while(word.indexOf(letter, index) != -1) {
                //get the index, and then change that index in the displayString to the correct character
                index = word.indexOf(letter, index);
                changeOutput(index, letter);
                // Add one to the index so the loop will look farther into the word
                ++index;
            }
        }
        else {
            ++wrongGuesses;
            ++totalGuesses;
            guessedLetters.append(letter);
            guessedLetters.append(", ");
        }

        // If there are no "spaces left" in the word, then the word has been filled in, and the game is over
        // Otherwise, the player has not guessed the word yet
        if(spacesLeft == 0) {
            return true;
        } else {
            return false;
        }
    }

    //These function exists to output the total number of guesses at the end
    public int getTotalGuesses() {
        return totalGuesses;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    @Override
    public String toString() {
        return "Current word: " + wordProgress.toString() + ", total guesses: " + totalGuesses +
                ", correct guesses: " + correctGuesses + ", incorrect guesses: " + wrongGuesses +
                ", previous incorrect guesses: " + guessedLetters.toString();
    }


}

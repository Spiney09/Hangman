import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WordMap {

    Map<Integer, String> words = new HashMap<Integer, String>();
    Map<Integer, Boolean> pickedWords = new HashMap<Integer, Boolean>();
    //This is used to determine how many games the user has already won. If it hits zero, it re-initializes
    int wordsLeft;

    WordMap() {
        initializeWords();
        initializePickedWords();
        wordsLeft = 10; // This initializes the number of words available.
    }

    // Puts the set words inside of the map of possible words. This way I only have to make one map
    // that I can pull from without needing to do anything but re-draw
    private void initializeWords() {
        words.put(0, "map");
        words.put(1, "nail");
        words.put(2, "bench");
        words.put(3, "knight");
        words.put(4, "digging");
        words.put(5, "absolute");
        words.put(6, "infection");
        words.put(7, "friendship");
        words.put(8, "challenging");
        words.put(9, "fabrications");
    }

    // This is used for the random number picking. The game checks this map, and sees if the word has already been
    // used previously.
    private void initializePickedWords() {
        for(int i = 0; i < 10; ++i) {
            pickedWords.put(i, false);
        }
    }

    // This resets the picked words, so the player can play again as soon as they have gone through all ten words
    private void resetWords() {
        for(int i = 0; i < 10; ++i) {
            pickedWords.replace(i, false);
        }
    }

    //This checks to see if the player has used all the words, and resets them if they have
    private void checkWordCount() {
        if(wordsLeft == 0) {
            resetWords();
        }
    }

    // This is called in the public getWord() function. It gets a random number between 1 and 10, and if that word has
    // not been played yet, it uses it. This does not scale very well, if I had more time I would implement a system
    // That pulls words out of one list and puts them into a second list. Then once the first list is empty, it would
    // start pulling words off of the second list and putting them on the first. But this system words for the time
    // that I have.
    private int getRandomWordNumber() {
        Random rand = new Random();
        boolean isNewWord = false;
        int number = -1;
        // This loop will iterate until a new word is found
        while(!isNewWord) {
            number = rand.nextInt(10);
            // Loop checks to see if the word has already been picked
            if(pickedWords.get(number) == false) {
                // Exit the loop
                isNewWord = true;
                // Set the word to register that it has been played
                pickedWords.replace(number, true);
                // Decrement the wordsLeft counter so it will eventually know when to reset them
                wordsLeft = wordsLeft - 1;
            }
        }
        return number;
    }

    public String getWord() {
        checkWordCount();
        int wordNum = getRandomWordNumber();
        String word = words.get(wordNum);
        return word;
    }

}

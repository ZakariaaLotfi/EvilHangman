import java.util.*;

public class EvilHangman {
    int numberOfGuesses;
    private Map<String, List<String>> families = new HashMap<>();
    private List<String> currentWordList;
    private String displayWord;
    private Set<Character> guessedLetters = new HashSet<>();
    private int MAX_GUESSES;
    private int wordLength;

    public EvilHangman(List<String> initialWordList, int wordLength, int lives) {
        this.currentWordList = initialWordList;
        this.displayWord = String.join("", Collections.nCopies(wordLength, "-"));
        this.numberOfGuesses = 0;
        this.wordLength = wordLength;
        this.MAX_GUESSES = lives;
    }
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Evil Hangman! Let's start the game.");
        while (!isGameOver()) {
            System.out.println("Current word: " + getDisplayWord());
            System.out.println("Used letters: " + guessedLetters);
            System.out.println("Number of guesses: " + numberOfGuesses);
            System.out.println("Enter your guess: ");
            char guess = scanner.next().charAt(0);
            makeGuess(guess);
        }
        if (isGameWon()) {
            System.out.println("Congratulations, you won! The word was: " + displayWord);
        } else {
            System.out.println("Sorry, you lost. The word was: " + currentWordList.get(0));
        }
        System.out.println("Thanks for playing Evil Hangman. See you next time!");
        scanner.close();
    }



    public void makeGuess(char guess) {
        guess = Character.toLowerCase(guess);
        if (guessedLetters.contains(guess)) {
            System.out.println("You already guessed that letter!");
            return;
        }
        guessedLetters.add(guess);

        families.clear();
        for (String word : currentWordList) {
            String family = getFamily(word, guess);
            if (!families.containsKey(family)) {
                families.put(family, new ArrayList<>());
            }
            families.get(family).add(word);
        }

        String largestFamily = getLargestFamily();
        currentWordList = families.get(largestFamily);
        updateDisplayWord(largestFamily, guess);
        numberOfGuesses++;
        System.out.println("Number of possible words left: " + currentWordList.size());

    }
    private void updateDisplayWord(String largestFamily, char guess) {
        StringBuilder newDisplayWord = new StringBuilder(displayWord);
        for (int i = 0; i < largestFamily.length(); i++) {
            if (largestFamily.charAt(i) == guess) {
                newDisplayWord.setCharAt(i, guess);
            }
        }
        displayWord = newDisplayWord.toString();
    }


    private String getFamily(String word, char guess) {
        StringBuilder family = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (c == guess) {
                family.append(c);
            } else {
                family.append('-');
            }
        }
        return family.toString();
    }

    private String getLargestFamily() {
        String largestFamily = "";
        int maxSize = 0;
        for (Map.Entry<String, List<String>> entry : families.entrySet()) {
            if (entry.getValue().size() > maxSize) {
                maxSize = entry.getValue().size();
                largestFamily = entry.getKey();
            }
        }
        return largestFamily;
    }

    public String getDisplayWord() {
        return displayWord;
    }

    private boolean isGameOver() {
        return isGameWon() || numberOfGuesses >= MAX_GUESSES;
    }
    private boolean isGameWon() {
        return !displayWord.contains("-");
    }
}

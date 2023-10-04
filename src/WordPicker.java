import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.security.SecureRandom;
import java.util.Scanner;

public class WordPicker {
    //private String word;
    // private Map<Integer, int[]> difficultyToLength = new HashMap<>();
    private ArrayList<String> words;
    private String displayWord;
    private int remainingGuesses;

    public WordPicker(int answer, int difficulty) throws IOException {
        String word = pickInitialWord(answer, difficulty);
    }

    public String pickInitialWord(int answer, int difficulty){//, int amount, int difficulty) throws IOException {
        File file = new File("src/words.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }
        DecisionTree decisionTree = new DecisionTree();
        HashMap<String, List<String>> wordMap = decisionTree.makeFamilies(words);
        String word;
        for ()

        // try (Scanner scanner = new Scanner(file)) {
        //     String tempWord = null;
        //     String word = null;
        //     SecureRandom random = new SecureRandom();
        //     int[] lengths = difficultyToLength.get(difficulty);
        //     int length = lengths[random.nextInt(2)];
        //     for (int i = 0; i < amount; i++) {
        //         if (!scanner.hasNextLine()) {
        //             throw new IllegalArgumentException("Not enough words in the file: " + file.getAbsolutePath());
        //         }
        //         tempWord = scanner.nextLine();
        //         if (tempWord.length() == length){
        //             word = tempWord;
        //         }
        //     }
        //     return word;
        // }

        return word;
    }
    public void EvilWords(int wordLength, int guesses) {
        words = new ArrayList<>();
        try {
            File file = new File("src/words.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                if (word.length() == wordLength) {
                    words.add(word);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> wordsAccessor(){
        return words;
    }

    // public String accessWord() {
    //     return word;
    // }
}
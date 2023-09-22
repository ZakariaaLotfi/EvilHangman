import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.security.SecureRandom;
import java.util.Scanner;

public class WordPicker {
    private String word;
    private Map<Integer, int[]> difficultyToLength = new HashMap<>();

    public WordPicker(int amount, int difficulty) throws IOException {
        difficultyToLength.put(0, new int[]{3, 4});
        difficultyToLength.put(1, new int[]{5, 6});
        difficultyToLength.put(2, new int[]{7, 8});
        word = pickWord(amount, difficulty);
    }

    public String pickWord(int amount, int difficulty) throws IOException {
        File file = new File("src/words.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }
        try (Scanner scanner = new Scanner(file)) {
            String tempWord = null;
            String word = null;
            SecureRandom random = new SecureRandom();
            int[] lengths = difficultyToLength.get(difficulty);
            int length = lengths[random.nextInt(2)];
            for (int i = 0; i < amount; i++) {
                if (!scanner.hasNextLine()) {
                    throw new IllegalArgumentException("Not enough words in the file: " + file.getAbsolutePath());
                }
                tempWord = scanner.nextLine();
                if (tempWord.length() == length){
                    word = tempWord;
                }
            }
            return word;
        }
    }

    public String accessWord() {
        return word;
    }
}
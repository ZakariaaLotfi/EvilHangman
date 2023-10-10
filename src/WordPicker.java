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

    public WordPicker(int difficulty) throws IOException {
        String word = pickInitialWord(difficulty);
    }
    public WordPicker(HashMap<String, List<String>> families){
        String word = pickWord(families);
    }

    public String pickInitialWord(int difficulty){//, int amount, int difficulty) throws IOException {
        ArrayList<String> words = new ArrayList<String>();
        File file = new File("src/words.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }
        try (Scanner scanner = new Scanner(file)) {
            String tempWord = null;
            String word = null;
            // for (int i = 0; i < amount; i++) {
            //     if (!scanner.hasNextLine()) {
            //         throw new IllegalArgumentException("Not enough words in the file: " + file.getAbsolutePath());
            //     }
            //     tempWord = scanner.nextLine();
            //     if (tempWord.length() == length){
            //         word = tempWord;
            //     }
            // }
            while (scanner.hasNextLine()){
                word = scanner.nextLine();
                if (word.length()==difficulty) words.add(word);
            }
            int length = 0;
            for (String w:words){
                length = w.length();
            }
            
            return word;
        }
    }
    public void EvilWords(int wordLength) {
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

    public String accessWord() {
        return word;
    }
}
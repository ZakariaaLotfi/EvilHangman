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

    public String pickInitialWord(int difficulty)throws IOException {
        File file = new File("src/words.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }
        Scanner scanner = new Scanner(file);
        String word;
        ArrayList<String> wordsOfLength = new ArrayList<String>();
        while (scanner.hasNextLine()){
            word = scanner.nextLine();
            if (word.length()==difficulty){
                wordsOfLength.add(word);
            }
        }
        SecureRandom secure = new SecureRandom();
        int randomnum = secure.nextInt(wordsOfLength.size() -1);

        return( wordsOfLength.get(randomnum));
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

    public String accessWord() {
        return word;
    }
}
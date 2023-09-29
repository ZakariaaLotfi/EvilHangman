import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EvilHangman {
    private ArrayList<String> words;
    private String displayWord;
    private int remainingGuesses;

    public EvilHangman(String filename, int wordLength, int guesses) {
        words = new ArrayList<>();
        try {
            File file = new File(filename);
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
    String blankWord = Game.createBlankWord(wordLength);
}}


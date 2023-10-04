import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EvilHangman {
    private ArrayList<String> words;
    private String displayWord;
    private int remainingGuesses;
    //private String blankWord = Game.createBlankWord(wordLength);

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
    }
    public EvilHangman() {
        System.out.println("Forgot parameters for evilhangman bozo");
    }
    public ArrayList<String> wordsAccessor(){
        return words;
    }
}


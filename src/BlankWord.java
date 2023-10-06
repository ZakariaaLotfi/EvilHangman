import java.util.ArrayList;
import java.util.StringTokenizer;

public class BlankWord {
    private ArrayList<String> keys = new ArrayList<String>();
    public String createBlankWord(String word, ArrayList<String> keys) {
        String blankWord = "";
        for (int i = 0; i < word.length(); i++) {
            blankWord += "_ ";
        }
        StringTokenizer st;
        for (int i=0; i<keys.size();i++){
            st = new StringTokenizer(keys.get(i), "", false);
        }
        blankWord = blankWord.substring(0, blankWord.length() - 1);
        return blankWord;
    }
}

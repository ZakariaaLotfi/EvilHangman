import java.util.ArrayList;
import java.util.StringTokenizer;

public class BlankWord {
    private ArrayList<String> keys = new ArrayList<String>();
    public String createBlankWord(String word, ArrayList<String> keys) {
        StringBuilder blankWord = new StringBuilder("");
        StringTokenizer st;
        String letter = "";
        for (int i=0; i<keys.size();i++){
            st = new StringTokenizer(keys.get(i), "", false);
            letter = st.nextToken();
            for (int j=0; j<word.length(); j++){
                String let = word.charAt(j)+"";
                if (let.equals(letter)){

                }         
            }
        }
        blankWord = blankWord.substring(0, blankWord.length() - 1);
        return blankWord;
    }
}

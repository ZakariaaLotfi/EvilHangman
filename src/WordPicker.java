import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WordPicker {
    private String word;
    private ArrayList<String> words;
    private ArrayList<String> keys = new ArrayList<>();
    private String blankWord = "";

    public WordPicker(int length) throws IOException {
        initialRun(length);
    }
    public WordPicker(String answer) throws IOException {
        run(answer);
    }
    public void initialRun(int length) throws IOException{
        word = pickInitialWord(length);
        blankWord = createBlankWord(word, keys);
    }
    public void run(String answer) throws IOException{
        word = pickWord(answer);
        blankWord = createBlankWord(word, keys);
    }
    public String pickInitialWord(int length) throws IOException {
        File file = new File("src/words.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            word = scanner.nextLine();
            if (word.length()==length){
                words.add(word);
            }
        }
        SecureRandom secure = new SecureRandom();
        int randomnum = secure.nextInt(words.size() -1);
        scanner.close();
        return(words.get(randomnum));
    }
    
    public String pickWord(String answer) {
        DecisionTree decisionTree = new DecisionTree();
        HashMap<String, ArrayList<String>> families = decisionTree.makeFamilies(answer, words);
        words.clear();
        families.forEach((i, j) -> {
            if (j.size()>words.size()) words = j;
        }); 
        word = "";
        words.forEach((i) -> {
            if (i.length()>word.length()) word = i;
        });
        ArrayList<String> keyList = new ArrayList<>(families.keySet());
        for (String i: keyList){
            if (families.get(i).contains(word)){
                keys.add(i); 
                break;
            }
        }
        return word;
    }

    public String createBlankWord(String word, ArrayList<String> keys) {
        if (keys.size()>0){
            StringBuilder blankword = new StringBuilder("");
            StringTokenizer st;
            String letter = "";
            for (int i=0; i<keys.size();i++){
                st = new StringTokenizer(keys.get(i), "", false);
                letter = st.nextToken();
                //getting every other token, hence calling nextToken twice
                letter = st.nextToken();
                for (int j=0; j<word.length(); j++){
                    String let = word.charAt(j)+"";
                    if (let.equals(letter)){
                        blankword.insert(j, let+" ");
                    }else{
                        blankword.insert(j, "_ ");
                    }
                }
            }
            blankWord = blankword.toString();
        }else{
            for (int i=0; i<word.length(); i++){
                blankWord += "_ ";
            }
        }
        blankWord = blankWord.substring(0, blankWord.length() - 1);
        return blankWord;
    }
    
    public String accessWord() {
        return word;
    }
    public ArrayList<String> accessKeys(){
        return keys;
    }
    public String accessBlankWord(){
        return blankWord;
    }
}
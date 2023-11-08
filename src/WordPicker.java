import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.security.SecureRandom;

public class WordPicker {
    private static String word;
    private static ArrayList<String> words = new ArrayList<>();
    private static ArrayList<String> keys = new ArrayList<>();
    private static String blankWord = "";

    public WordPicker(int length) throws IOException {
        word = pickWord(length);
        blankWord = createBlankWord(word, keys);
    }

    public WordPicker(String answer) throws IOException {
        word = pickWord(answer);
        blankWord = createBlankWord(word, keys);
    }


    public void addWords(int length) throws IOException {
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
        scanner.close();

    }




    public String pickWord(int length) throws IOException {
        addWords(length);
        SecureRandom secure = new SecureRandom();
        int randomnum = secure.nextInt(words.size() -1);
        return(words.get(randomnum));
    }

    public String pickWord(String answer) {
        DecisionTree decisionTree = new DecisionTree();
        HashMap<String, ArrayList<String>> families = decisionTree.makeFamilies(answer, words);
        words.clear();
        ArrayList<String> keyList = new ArrayList<>(families.keySet());
        for (String i: keyList){
            if (families.get(i).contains(word)){
                keys.add(i);
                words = families.get(i);
                break;
            }
        }
        SecureRandom rand = new SecureRandom();
        word = words.get(rand.nextInt(words.size()));
        return word;
    }
    public static ArrayList<String> splitArrayList(ArrayList<String> keys){
        ArrayList<String> key = new ArrayList<String>();
        for (int i=0; i<keys.size();i++){
            Collections.addAll(key, keys.get(i).split(""));
        }
        return key;
    }
    public String addSpaces(StringBuilder strB, String blankWord){
        for (int i=0; i<strB.length()-1; i++) if (i%2==1) strB.insert(i, " ");
        return strB.toString();
    }
    public String createBlankWord(String word, ArrayList<String> keys) {
        if (keys.size()>0){
            StringBuilder blankWordBuilder = new StringBuilder("");
            blankWord = "";
            ArrayList<String> key = new ArrayList<String>();
            key = splitArrayList(keys);
            for (int k=0; k<word.length(); k++){
                String let = word.charAt(k)+"";
                if (key.contains(let)){
                    blankWordBuilder.insert(k, let);
                }else{
                    blankWordBuilder.insert(k, "-");
                }
            }
            blankWord = blankWordBuilder.toString();
        }else{
            for (int i=0; i<word.length(); i++) blankWord += "-";
        }
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
    public ArrayList<String> accessWords(){
        return words;
    }
}
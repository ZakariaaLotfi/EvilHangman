import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.security.SecureRandom;
import java.util.Scanner;

public class WordPicker {
    private static String word;
    private static ArrayList<String> words = new ArrayList<>();
    private static ArrayList<String> keys = new ArrayList<>();
    private static String blankWord = "";

    public WordPicker(int length) throws IOException {
        word = pickInitialWord(length);
        blankWord = createBlankWord(word, keys);
    }
    public WordPicker(String answer) throws IOException {
        word = pickWord(answer);
        blankWord = createBlankWord(word, keys);
    }
    // public void initialRun(int length) throws IOException{
    //     word = pickInitialWord(length);
    //     blankWord = createBlankWord(word, keys);
    // }
    // public void run(String answer) throws IOException{
    //     word = pickWord(answer);
    //     System.out.println("ihjqefgbiwyqf::"+word);
    //     blankWord = createBlankWord(word, keys);
    // }
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
        System.out.println("jjqq"+words);
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
        word = "";
        words.forEach((i) -> {
            if (i.length()>word.length()) word = i;
        });
        System.out.println("Word is: "+word);
        System.out.println("Words is: "+words);
        return word;
    }

    public String createBlankWord(String word, ArrayList<String> keys) {
        if (keys.size()>0){
            StringBuilder blankword = new StringBuilder("");
            blankWord = "";
            String letter = "";
            for (int i=0; i<keys.size();i++){
                ArrayList<String> key = new ArrayList<String>();
                Collections.addAll(key, keys.get(i).split(""));
                for (int j=1; j<key.size(); j+=2){
                    letter = key.get(j);
                    for (int k=0; k<word.length(); k++){
                        String let = word.charAt(k)+"";
                        if (let.equals(letter)){
                            blankword.insert(k, let+" ");
                        }else{
                            blankword.insert(k, "_ ");
                        }
                    }
                }
            }
            blankWord = blankword.toString();
        }else{
            System.out.println("jjjj");
            System.out.println(word.length());
            for (int i=0; i<word.length(); i++){
                blankWord += "_ ";
                System.out.println("k");
            }
            System.out.println("jjhjj"+blankWord);
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
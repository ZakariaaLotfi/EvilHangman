import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.security.SecureRandom;
import java.util.Scanner;

public class WordPicker {
    private String word;
    private ArrayList<String> words;

    public WordPicker(int length) throws IOException {
        word = pickInitialWord(length);
    }
    public WordPicker(String answer) throws IOException {
        word = pickWord(answer);
    }

    public String pickInitialWord(int length)throws IOException {
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
        return word;
    }
    
    public String accessWord() {
        return word;
    }
}
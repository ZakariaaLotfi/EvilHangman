import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTree
{
    public HashMap<String, ArrayList<String>> makeFamilies(String answer, ArrayList<String> list) {
        HashMap<String, ArrayList<String>> wordFamilies = new HashMap<>();
        String key;
        for (String word: list) {
            key = "";
            for(int i=0; i<word.length();i++){
                String let = word.charAt(i)+"";
                let = let.toLowerCase();
                if (let.equals(answer)){
                    key += Integer.toString(i)+let;
                }
            }
            if (wordFamilies.containsKey(key)){
                ArrayList<String> wordsInFamily = wordFamilies.get(key);
                wordsInFamily.add(word);
                wordFamilies.put(key, wordsInFamily);
            }else{
                ArrayList<String> wordsInFamily = new ArrayList<String>();
                wordsInFamily.add(word);
                wordFamilies.put(key, wordsInFamily);
            }
        }
        System.out.println("Heyhey"+wordFamilies);
        return wordFamilies;
    }
}



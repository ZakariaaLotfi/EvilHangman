import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecisionTree
{
    
    public HashMap<String, List<String>> makeFamilies(String answer, ArrayList<String> list) {
        HashMap<String, List<String>> wordFamilies = new HashMap<>();

        String key;
        for (String word: list) {
            key = "";
            for(int i=0; i<word.length();i++){
                String let = word.charAt(i)+"";
                if (let.equals(answer)){
                    key += " ".repeat(i)+let;
                }
            }
            if (wordFamilies.containsKey(key)){
                List<String> wordsInFamily = wordFamilies.get(key);
                wordsInFamily.add(word);
                wordFamilies.put(key, wordsInFamily);
            }else{
                ArrayList<String> wordsInFamily = new ArrayList<String>();
                wordsInFamily.add(word);
                wordFamilies.put(key, wordsInFamily);
            }
            // StringBuilder sb = new StringBuilder();
            // for (int i = 0; i < word.length(); i++) {
            //     sb.append(word.charAt(i));
            // }
            // String key = sb.toString();

            // List<String> family = wordFamilies.getOrDefault(key, new ArrayList<>());
            // family.add(word);
            // wordFamilies.put(key, family);
        }
        return wordFamilies;
    }
}



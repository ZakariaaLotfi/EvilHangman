import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecisionTree {

    public Map<String, List<String>> record(ArrayList<String> list) {
        HashMap<String, List<String>> wordFamilies = new HashMap<>();

        for (String word : list) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(word.charAt(i));
            }
            String key = sb.toString();

            List<String> family = wordFamilies.getOrDefault(key, new ArrayList<>());
            family.add(word);
            wordFamilies.put(key, family);
        }


        return wordFamilies;
    }
}




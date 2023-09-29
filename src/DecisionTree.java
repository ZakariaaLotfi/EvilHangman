public class DecisionTree
{
    
    public void record(ArrayList<String> list) {
        HashMap<String, List<String>> wordFamilies = new HashMap<>();

        for (String word = words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(word.charAt(i)).append(",");
            }
            String key = sb.toString();

            List<String> family = wordFamilies.getOrDefault(key, new ArrayList<>());
            family.add(word);
            wordFamilies.put(key, family);
        }


        return wordFamilies;
    }
    }
}



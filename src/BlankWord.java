// import java.util.ArrayList;
// import java.util.Set;
// import java.util.StringTokenizer;

// public class BlankWord {
//     public String createBlankWord(String word, ArrayList<String> keys) {
//         StringBuilder blankword = new StringBuilder("");
//         StringTokenizer st;
//         String letter = "";
//         for (int i=0; i<keys.size();i++){
//             st = new StringTokenizer(keys.get(i), "", false);
//             letter = st.nextToken();
//             for (int j=0; j<word.length(); j++){
//                 String let = word.charAt(j)+"";
//                 if (let.equals(letter)){
//                     blankword.insert(j, let+" ");
//                 }else{
//                     blankword.insert(j, "_ ");
//                 }
//             }
//         }
//         String blankWord = blankword.toString();
//         blankWord = blankWord.substring(0, blankWord.length() - 1);
//         return blankWord;
//     }
// }

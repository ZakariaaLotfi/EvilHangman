import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;

public class Game{
    public Game(){
    }
    
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static String userStringChoices(Scanner s, String question,String[] possibleAnswers){
        String answer;
        while (true){
            System.out.println(question);
            answer = s.nextLine().toLowerCase();
            for (int i=0; i<possibleAnswers.length; i++){
                if (answer.equals(possibleAnswers[i])){
                    return answer;
                }
            }
        }
    }

    public static int checkLetter(Scanner s, String[] letters, ArrayList<String> chosen, ArrayList<String> alph){
        while (true){
            String answer;
            System.out.println("Enter a letter");
            answer = s.nextLine().toUpperCase();
            if (alph.contains(answer.toUpperCase())){
                int inWord = 0;
                System.out.println(letters);
                for (String i:letters){
                    System.out.println(i);
                    if (answer.equals(i)){
                        inWord = 1;
                        break;
                    }
                }
                if (inWord==0){
                    return 0;
                }else {
                    if (chosen.contains(answer)){
                        System.out.println("Letter already chosen");
                    }else{
                        chosen.add(answer);
                        return 1;
                    }
                    
                }
            }
        }
    }
    
    public static int intro(){
        System.out.println("Welcome to hangman!");
        Scanner scanner = new Scanner(System.in);
        String[] difficulties = new String[] {"easy", "medium", "hard"};
        String difficultyString = userStringChoices(scanner, "What difficulty? (easy/medium/hard)",difficulties);
        if (difficultyString.equals("easy")){
            return 0;
        }else if (difficultyString.equals("medium")){
            return 1;
        }
        return 2;
    }

    public static void guessing(int difficulty) throws IOException{
        RandomNum numObj = new RandomNum();
        int num = numObj.numAccessor();
        WordPicker wordObj = new WordPicker(num, difficulty);
        String word = wordObj.accessWord().toUpperCase();
        String[] letters = word.split("");
        System.out.println(word);
        //System.out.println(letters);
        String blankWord = "";
        for (int i=0; i<word.length(); i++){
            blankWord += "_ ";
        }
        blankWord = blankWord.substring(0, blankWord.length()-1);
        Hangman man = new Hangman();
        // String[] stages = man.stagesAccessor();
        // int lives = man.livesAccessor();
        String stage = man.newStage();
        System.out.println(stage);
        System.out.println(blankWord);
        String[] alphs = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ArrayList<String> alphabet = new ArrayList<String>();
        for (String a:alphs){
            alphabet.add(a);
        }
        ArrayList<String> chosen = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (i!=word.length()){
            int correct = checkLetter(scanner, letters, chosen, alphabet);
            if (correct==1){
                System.out.println("Correct!");
                i++;
            }else{
                System.out.println("Wrong!");
            }
        }
    }

    public void game() throws IOException{
        int difficulty = intro();
        guessing(difficulty);
    }
}
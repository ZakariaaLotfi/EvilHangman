import java.io.IOException;
import java.util.Scanner;

public class Game{
    public Game(){
    }
    
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static String userStringChoices(Scanner s, String question, String[] possibleAnswers){
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
    
    public static int intro(){
        System.out.println("Welcome to hangman!");
        Scanner scanner = new Scanner(System.in);
        String[] difficulties = new String[] {"easy", "medium", "hard"};
        String difficultyString = userStringChoices(scanner, "What difficulty would you like? (easy/medium/hard)", difficulties);
        if (difficultyString.equals("easy")){
            return 0;
        }else if (difficultyString.equals("medium")){
            return 1;
        }
        return 2;
    }

    public static void guessing(int difficulty) throws IOException{
        Hangman man = new Hangman();
        RandomNum numObj = new RandomNum();
        int num = numObj.numAccessor();
        WordPicker wordObj = new WordPicker(num, difficulty);
        String word = wordObj.accessWord();
        String blankWord = "";
        for (int i=0; i<word.length(); i++){
            blankWord += "_ ";
        }
        blankWord = blankWord.substring(0, blankWord.length()-1);
        System.out.println(word);
        System.out.println(blankWord);
    }

    public void game() throws IOException{
        int difficulty = intro();
        guessing(difficulty);
    }
}
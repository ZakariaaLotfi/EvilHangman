import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;

public class Game{
    public Game() throws IOException{
        game();
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

    public static int checkLetter(Scanner s, String answer, String[] letters, ArrayList<String> chosen, ArrayList<String> alph){
        while (true){
            // String answer;
            // System.out.println("Enter a letter");
            // answer = s.nextLine().toUpperCase();
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

    public static int guessing(int difficulty) throws IOException{
        RandomNum numObj = new RandomNum();
        int num = numObj.numAccessor();
        WordPicker wordObj = new WordPicker(num, difficulty);
        String word = wordObj.accessWord().toUpperCase();
        String[] letters = word.split("");
        System.out.println(word);
        //System.out.println(letters);
        StringBuilder blankWord = new StringBuilder("");
        for (int i=0; i<word.length(); i++){
            blankWord += "_ ";
        }
        blankWord = blankWord.substring(0, blankWord.length()-1);
        Hangman man = new Hangman();
        // String[] stages = man.stagesAccessor();
        // int lives = man.livesAccessor();
        String stage = man.newStage();
        String[] alphs = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ArrayList<String> alphabet = new ArrayList<String>();
        for (String a:alphs){
            alphabet.add(a);
        }
        ArrayList<String> chosen = new ArrayList<String>();
        Scanner s = new Scanner(System.in);
        String answer;
        int i = 0;
        while (i<(word.length()-1)){
            System.out.println("Enter a letter");
            answer = s.nextLine().toUpperCase();
            if (man.livesAccessor()!=0){
                System.out.println(stage);
                System.out.println(blankWord);
                int correct = checkLetter(s, answer, letters, chosen, alphabet);
                if (correct==1){
                    for (int j=0; j<answer.length(); j++){
                        char let = answer.charAt(j);
                        int blankIndex = blankWord.indexOf(let);
                        blankWord.setCharAt();
                    }
                    System.out.println("Correct!");
                    i++;
                }else{
                    System.out.println("Wrong!");
                    stage = man.newStage();
                }
                try {
                    Thread.sleep(1500);
                } catch(InterruptedException e) {
                    System.out.println("got interrupted!");
                }
                clearScreen();
            }else{
                return 0;
            }
        }
        s.close();
        return i;
    }

    public void game() throws IOException{
        int difficulty = intro();
        if (guessing(difficulty)==1){
            System.out.println("You won. yay.");
        }else{
            System.out.println("WOMP WOMP. YOU LOSE!");
        }
    }
}
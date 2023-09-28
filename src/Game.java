import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    public Game() throws IOException {
        Hangman man = new Hangman();
        game(man);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String updateBlankWord(String answer, String word, String blankWord) {
        for (int j = 0; j < word.length() * 2; j += 2) {
            String letter = word.charAt(j / 2) + "";
            if (letter.equals(answer)) {
                blankWord = blankWord.substring(0, j) + answer + blankWord.substring(j + 1);
            }
        }
        return blankWord;
    }

    public static String userStringChoices(Scanner s, String question, String[] possibleAnswers) {
        String answer;
        while (true) {
            System.out.println(question);
            answer = s.nextLine().toLowerCase();
            for (int i = 0; i < possibleAnswers.length; i++) {
                if (answer.equals(possibleAnswers[i])) {
                    return answer;
                }
            }
        }
    }

    public static int checkLetter(Scanner s, String answer, String[] letters, ArrayList<String> chosen, ArrayList<String> alph) {
        if (alph.contains(answer.toUpperCase())) {
            int inWord = 0;
            for (String i : letters) {
                System.out.println(i);
                if (answer.equals(i)) {
                    inWord = 1;
                    break;
                }
            }
            if (chosen.contains(answer)) {
                System.out.println("Letter already chosen");
            } else {
                if (inWord == 0) {
                    chosen.add(answer);
                    return 0;
                } else {
                    chosen.add(answer);
                    return 1;
                }
            }
        }
        return 2;
    }

    public static int intro() {
        System.out.println("Welcome to hangman!");
        Scanner scanner = new Scanner(System.in);
        String[] difficulties = new String[]{"easy", "medium", "hard"};
        String difficultyString = userStringChoices(scanner, "What difficulty? (easy/medium/hard)(Evil)", difficulties);
        if (difficultyString.equals("easy")) {
            return 0;
        } else if (difficultyString.equals("medium")) {
            return 1;
        } else if (difficultyString.equals("hard")) {


            return 2;
        } else {
            return 3;
        }
    }


    public static int guessing(int difficulty, Hangman man) throws IOException{
        RandomNum numObj = new RandomNum();
        int num = numObj.numAccessor();
        WordPicker wordObj = new WordPicker(num, difficulty);
        String word = wordObj.accessWord().toUpperCase();
        String[] letters = word.split("");
        System.out.println(word);
        String blankWord = "";
        for (int i=0; i<word.length(); i++){
            blankWord += "_ ";
        }
        blankWord = blankWord.substring(0, blankWord.length()-1);
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
        while (i<=(word.length())){
            clearScreen();
            if (man.livesAccessor()!=0){
                System.out.println(stage);
                System.out.println();
                System.out.println(blankWord);
                System.out.println("Enter a letter");
                answer = s.nextLine().toUpperCase();
                int correct = checkLetter(s, answer, letters, chosen, alphabet);
                if (correct==1){
                    blankWord = updateBlankWord(answer, word, blankWord);
                    System.out.println("Correct!");
                    i++;
                }else if (correct==0){
                    System.out.println("Wrong!");
                    stage = man.newStage();
                }
                try {
                    Thread.sleep(1500);
                } catch(InterruptedException e) {
                    System.out.println("failed to sleep");
                }
                String blankWordSansSpace = blankWord.replaceAll(" ", "");
                if (blankWordSansSpace.equals(word)){
                    clearScreen();
                    return 1;
                }
            }else{
                return 0;
            }
        }
        s.close();
        return 1;
    }

    public void game(Hangman man) throws IOException{
        int difficulty = intro();
        int victoryStatus = guessing(difficulty, man);
        System.out.println(man.stageAccessor()+"\n");
        if (victoryStatus==1){
            System.out.println("You won. yay.");
        }else if(victoryStatus==0){
            System.out.println("WOMP WOMP. YOU LOSE!");
        }
    }
}
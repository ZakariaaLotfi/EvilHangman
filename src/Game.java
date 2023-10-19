import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static int userIntChoices(Scanner s, String question, int[] possibleAnswers) {
        String answer;
        while (true) {
            System.out.println(question);
            answer = s.nextLine();
            for (int i: possibleAnswers) {
                if (isNumeric(answer)) {
                    if (Integer.parseInt(answer) == i) return Integer.parseInt(answer);
                }
            }
        }
    }

    public static String userStringChoices(Scanner s, String question, String[] possibleAnswers) {
        String answer;
        while (true) {
            System.out.println(question);
            answer = s.nextLine();
            for (String i: possibleAnswers) {
                if (i.equals(answer)) {
                    return answer;
                }
            }
        }
    }

    public static int checkLetter(Scanner s, String answer, ArrayList<String> letters) {
        int inWord = 0;
        for (String i : letters) {
            System.out.println(i);
            if (answer.equals(i)) {
                inWord = 1;
                break;
            }
        }
        if (inWord == 0) {
            return 0;
        }
        return 1;
    }

    public static int intro() throws IOException {
        System.out.println("Welcome to hangman!");
        Scanner scanner = new Scanner(System.in);
        int[] lengths = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int wordLength = userIntChoices(scanner, "What length? (Evil)", lengths);
        return wordLength;
    }

    public static int guessing(int wordLength, Hangman man) throws IOException {
        WordPicker wordPicker = new WordPicker(wordLength);
        String word = wordPicker.accessWord();
        String blankWord = wordPicker.accessBlankWord();
        boolean won = false;
        String stage = man.newStage();
        while (!won){
            if (!blankWord.contains(" ")) won=true;
            if (man.livesAccessor()==0) return 0;
            clearScreen();
            ArrayList<Object> answerAndCorrect = module(man, stage, word, blankWord);
            int correct = (int)(answerAndCorrect.get(1));
            String answer = answerAndCorrect.get(0).toString();
            if (correct==1){
                wordPicker = new WordPicker(answer);
                word = wordPicker.accessWord();
                blankWord = wordPicker.accessBlankWord();
            }else{
                stage = man.newStage();
            }
            if (!blankWord.contains("_")) won = true;
        }
        return 1;
    }

    public static ArrayList<Object> module(Hangman man, String stage, String word, String blankWord){
        Scanner scanner = new Scanner(System.in);
        System.out.println(stage);
        System.out.println();
        System.out.println(blankWord);
        //System.out.println(word);
        ArrayList<String> letters = new ArrayList<>();
        System.out.println(letters);
        for (int i = 0; i < word.length(); i++) {
            letters.add(word.charAt(i)+"");
        }
        String[] alph = "abcdefghijklmnopqrstuvwxyz".split("");
        String answer = userStringChoices(scanner, "Enter a letter:", alph);
        System.out.println("sjkkw");
        Integer correct = checkLetter(scanner, answer, letters);
        ArrayList<Object> answerAndCorrect = new ArrayList<Object>();
        answerAndCorrect.add(answer);
        answerAndCorrect.add(correct);
        return answerAndCorrect;
    }

    public void game(Hangman man) throws IOException {
        int wordLength = intro();
        int victoryStatus = guessing(wordLength, man);
        clearScreen();
        System.out.println(man.stageAccessor() + "\n");
        if (victoryStatus == 1) {
            System.out.println(88 + "You won. yay." + 88);
        } else if (victoryStatus == 0) {
            System.out.println("WOMP WOMP. YOU LOSE!");
        }
    }
}
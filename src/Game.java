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
            answer = s.nextLine().toLowerCase();
            for (String i: possibleAnswers) {
                if (i.equals(answer)) {
                    return answer;
                }
            }
        }
    }

    public static int checkLetter(Scanner s, String answer, ArrayList<String> letters, ArrayList<String> alph) {
        if (alph.contains(answer.toUpperCase())) {
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
        return 2;
    }

    public static String[] intro() throws IOException {
        System.out.println("Welcome to hangman!");
        Scanner scanner = new Scanner(System.in);
        int[] lengths = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int wordLength = userIntChoices(scanner, "What length? (Evil)", lengths);
        WordPicker wordPicker = new WordPicker(wordLength);
        String word = wordPicker.accessWord();
        String blankWord = wordPicker.accessBlankWord();
        String[] wordBlankWord = new String[]{word, blankWord};
        return wordBlankWord;
    }

    // public static String createBlankWord(String word, ArrayList<String> keys) {
    //     BlankWord blankWord = new BlankWord();
    //     String blankword = blankWord.createBlankWord(word, keys);
    // }

    public static int guessing(String[] wordBlankWord, Hangman man) throws IOException {

        boolean done = false;
        String stage = man.newStage();

        while (!done){

            module(man, stage, wordBlankWord);
        }

    }

    public static void module(Hangman man, String stage, ArrayList<String> wordBlankWord){

        Scanner scanner = new Scanner(System.in);
        System.out.println(stage);
        System.out.println();
        System.out.println(wordBlankWord.get(1));
        ArrayList<String> letters = new ArrayList<>();
        for (int i = 0; i < wordBlankWord.get(0).length(); i++) {
                letters.add(wordBlankWord.get(0).charAt(i)+"");
        }
        String[] alph = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String answer = userStringChoices(scanner, "Enter a letter:",alph);
        int correct = checkLetter(scanner, answer, letters, alph);

    }

    public void game(Hangman man) throws IOException {
        String[] word = intro();
        int victoryStatus = guessing(word, man);
        System.out.println(man.stageAccessor() + "\n");
        if (victoryStatus == 1) {
            System.out.println(88 + "You won. yay." + 88);
        } else if (victoryStatus == 0) {
            System.out.println("WOMP WOMP. YOU LOSE!");
        }
    }
}
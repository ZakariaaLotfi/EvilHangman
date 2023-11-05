import java.io.File;
import java.io.IOException;
import java.util.Scanner;
//import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import javax.sound.sampled.*;

public class Game {
    private static HashSet<String> chosen = new HashSet<String>();
    private static String answer = "";
    private static final String[] lAlph = "abcdefghijklmnopqrstuvwxyz".split("");
    private static ArrayList<String> alph = new ArrayList<String>(Arrays.asList(lAlph));
    private static ArrayList<String> chosenList = new ArrayList<String>();
    private static String word;
    private static LinkedHashSet<String> allWords = new LinkedHashSet<String>();

    public static void setChosen(HashSet<String> chosen, ArrayList<String> alph, String answer){
        chosen.add(answer);
        chosenList = new ArrayList<String>(chosen);
        Collections.sort(chosenList);
        chosenList.remove(0);
    }

    public Game() throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        Hangman man = new Hangman();
        game(man);
    }

    public static void addToArrayList(String item) {
        chosen.add(item);
        for (String string : chosen) {
            System.out.print("Letters picked: " + string + " ");
        }
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

    public static String userStringChoices(Scanner s, String question, ArrayList<String> alph) {
        String answer;
        while (true) {
            System.out.println(question);
            answer = s.nextLine();
            for (String i: alph) {
                if (i.equals(answer)) {
                    return answer;
                }
            }
        }
    }

    public static int checkLetter(Scanner s, String answer, ArrayList<String> letters) {
        int inWord = 0;
        for (String i : letters) {
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

    public static int[] intro() throws IOException {
        System.out.println("Welcome to Evil Hangman!");
        Scanner scanner = new Scanner(System.in);
        int[] lengths = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] livesA = new int[25];
        for (int i=0; i<livesA.length; i++){
            livesA[i] = i+1;
        }
        int wordLength = userIntChoices(scanner, "What length? (2-20)", lengths);
        int lives = userIntChoices(scanner, "How many lives? (1-25)", livesA);
        return new int[]{wordLength, lives};
    }

    public static void playMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner sc = new Scanner(System.in);
        File file = new File("src/At Doom's Gate.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(clip.LOOP_CONTINUOUSLY);
        clearScreen();
        System.out.println("\r\n" + //
                " ____    __  __  ______   __          __  __  ______  __  __  ____             ______  __  __     \r\n" + //
                "/\\  _`\\ /\\ \\/\\ \\/\\__  _\\ /\\ \\        /\\ \\/\\ \\/\\  _  \\/\\ \\/\\ \\/\\  _`\\   /'\\_/`\\/\\  _  \\/\\ \\/\\ \\    \r\n" + //
                "\\ \\ \\L\\_\\ \\ \\ \\ \\/_/\\ \\/ \\ \\ \\       \\ \\ \\_\\ \\ \\ \\L\\ \\ \\ `\\\\ \\ \\ \\L\\_\\/\\      \\ \\ \\L\\ \\ \\ `\\\\ \\   \r\n" + //
                " \\ \\  _\\L\\ \\ \\ \\ \\ \\ \\ \\  \\ \\ \\  __   \\ \\  _  \\ \\  __ \\ \\ , ` \\ \\ \\L_L\\ \\ \\__\\ \\ \\  __ \\ \\ , ` \\  \r\n" + //
                "  \\ \\ \\L\\ \\ \\ \\_/ \\ \\_\\ \\__\\ \\ \\L\\ \\   \\ \\ \\ \\ \\ \\ \\/\\ \\ \\ \\`\\ \\ \\ \\/, \\ \\ \\_/\\ \\ \\ \\/\\ \\ \\ \\`\\ \\ \r\n" + //
                "   \\ \\____/\\ `\\___/ /\\_____\\\\ \\____/    \\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\ \\____/\\ \\_\\\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\\r\n" + //
                "    \\/___/  `\\/__/  \\/_____/ \\/___/      \\/_/\\/_/\\/_/\\/_/\\/_/\\/_/\\/___/  \\/_/ \\/_/\\/_/\\/_/\\/_/\\/_/\r\n" + //
                "                                                                                                  \r\n" + //
                "                                                                                                  \r\n" + //
                "");
        System.out.println("Press enter to start");
        String response = sc.nextLine();
        clearScreen();
    }

    public static int guessing(Scanner scanner, int wordLength, Hangman man, int lives) throws IOException, InterruptedException {
        WordPicker wordPicker = new WordPicker(wordLength);
        System.out.println(wordPicker);
        word = wordPicker.accessWord();
        String blankWord = wordPicker.accessBlankWord();
        boolean won = false;
        String stage = man.newStage();
        clearScreen();
        String returnedBlankWord = blankWord;
        ArrayList<Object> ansCorrectBlank = module(scanner, man, stage, word, blankWord, wordPicker, lives);
        returnedBlankWord = ansCorrectBlank.get(1).toString();
        if (returnedBlankWord.equals(blankWord)) lives--;
        while (!won){
            if (lives==0) return 0;
            clearScreen();
            ansCorrectBlank = module(scanner, man, stage, word, blankWord, wordPicker, lives);
            String answer = ansCorrectBlank.get(0).toString();
            returnedBlankWord = ansCorrectBlank.get(1).toString();
            wordPicker = new WordPicker(answer);
            word = wordPicker.accessWord();
            blankWord = wordPicker.accessBlankWord();
            if (returnedBlankWord.equals(blankWord)) lives--;
            if (!blankWord.contains("_")) won = true;
        }
        return 1;
    }

    public static ArrayList<Object> module(Scanner scanner, Hangman man, String stage, String word, String blankWord, WordPicker wordPicker, int lives){
        allWords.add(word);
        System.out.println("Lives left: "+lives);
        System.out.println();
        System.out.println(blankWord);
        setChosen(chosen, alph, answer);
        System.out.println(chosenList);
        //System.out.println(word);
        ArrayList<String> letters = new ArrayList<>();
        //System.out.println(letters);
        for (int i = 0; i < word.length(); i++) {
            letters.add(word.charAt(i)+"");
        }
        System.out.println("Possible words: "+wordPicker.accessWords().size());
        //System.out.println("kkk"+prevBlank);
        answer = userStringChoices(scanner, "Enter a letter:", alph);
        System.out.println(blankWord);
        ArrayList<Object> ansCorrectBlank = new ArrayList<Object>();
        ansCorrectBlank.add(answer);
        ansCorrectBlank.add(blankWord);
        return ansCorrectBlank;
    }

    public void game(Hangman man) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        Scanner scanner = new Scanner(System.in);
        playMusic();
        int[] wordNLives = intro();
        int wordLength = wordNLives[0];
        int lives = wordNLives[1];
        int victoryStatus = guessing(scanner, wordLength, man, lives);
        clearScreen();
        ArrayList<String> allWordsL = new ArrayList<String>();
        allWordsL.addAll(allWords);
        System.out.println("Words: "+allWords);
        System.out.println("Final Word: "+allWordsL.get(allWords.size()-1));
        if (victoryStatus == 1) {
            String youWon = " __    __  _____       __  __                 __      __     _____       __  __     \r\n" + //
                    "/\\ \\  /\\ \\/\\  __`\\    /\\ \\/\\ \\               /\\ \\  __/\\ \\   /\\  __`\\    /\\ \\/\\ \\    \r\n" + //
                    "\\ `\\`\\\\/'/\\ \\ \\/\\ \\   \\ \\ \\ \\ \\              \\ \\ \\/\\ \\ \\ \\  \\ \\ \\/\\ \\   \\ \\ `\\\\ \\   \r\n" + //
                    " `\\ `\\ /'  \\ \\ \\ \\ \\   \\ \\ \\ \\ \\              \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\   \\ \\ , ` \\  \r\n" + //
                    "   `\\ \\ \\   \\ \\ \\_\\ \\   \\ \\ \\_\\ \\              \\ \\ \\_/ \\_\\ \\  \\ \\ \\_\\ \\   \\ \\ \\`\\ \\ \r\n" + //
                    "     \\ \\_\\   \\ \\_____\\   \\ \\_____\\              \\ `\\___x___/   \\ \\_____\\   \\ \\_\\ \\_\\\r\n" + //
                    "      \\/_/    \\/_____/    \\/_____/               '\\/__//__/     \\/_____/    \\/_/\\/_/";
            System.out.println(youWon);
        } else if (victoryStatus == 0) {
            String youLost = " _        _            _          _                          _              _               _               _       \r\n" + //
                    "/\\ \\     /\\_\\         /\\ \\       /\\_\\                       _\\ \\           /\\ \\            / /\\            /\\ \\     \r\n" + //
                    "\\ \\ \\   / / /        /  \\ \\     / / /         _            /\\__ \\         /  \\ \\          / /  \\           \\_\\ \\    \r\n" + //
                    " \\ \\ \\_/ / /        / /\\ \\ \\    \\ \\ \\__      /\\_\\         / /_ \\_\\       / /\\ \\ \\        / / /\\ \\__        /\\__ \\   \r\n" + //
                    "  \\ \\___/ /        / / /\\ \\ \\    \\ \\___\\    / / /        / / /\\/_/      / / /\\ \\ \\      / / /\\ \\___\\      / /_ \\ \\  \r\n" + //
                    "   \\ \\ \\_/        / / /  \\ \\_\\    \\__  /   / / /        / / /          / / /  \\ \\_\\     \\ \\ \\ \\/___/     / / /\\ \\ \\ \r\n" + //
                    "    \\ \\ \\        / / /   / / /    / / /   / / /        / / /          / / /   / / /      \\ \\ \\          / / /  \\/_/ \r\n" + //
                    "     \\ \\ \\      / / /   / / /    / / /   / / /        / / / ____     / / /   / / /   _    \\ \\ \\        / / /        \r\n" + //
                    "      \\ \\ \\    / / /___/ / /    / / /___/ / /        / /_/_/ ___/\\  / / /___/ / /   /_/\\__/ / /       / / /         \r\n" + //
                    "       \\ \\_\\  / / /____\\/ /    / / /____\\/ /        /_______/\\__\\/ / / /____\\/ /    \\ \\/___/ /       /_/ /          \r\n" + //
                    "        \\/_/  \\/_________/     \\/_________/         \\_______\\/     \\/_________/      \\_____\\/        \\_\\/           \r\n" + //
                    "                                                                                                                    \r\n" + //
                    "";
            System.out.println(youLost);
        Thread.sleep(3000);
        }
    }
}
import java.io.IOException;

public class Game{
    public Game(){
    }
    
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
  
    public void game() throws IOException{
        System.out.println("Welcome to hangman!");
        RandomNum numObj = new RandomNum();
        int num = numObj.accessor();
        WordPicker wordObj = new WordPicker(num);
        String word = wordObj.accessWord();
        System.out.println(word);
        
    }
}
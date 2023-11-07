import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class Main {
    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
      List<String> words = Arrays.asList("ALLY", "BETA" ,"COOL" ,"DEAL" ,"ELSE" ,"FLEW", "GOOD", "HOPE", "IBEX");
      EvilHangman game2 = new EvilHangman(words);
      game2.playGame();
      Game game = new Game();
    }
  }

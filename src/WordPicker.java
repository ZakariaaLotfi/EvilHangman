import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class WordPicker {
    private String word;

    public WordPicker(int amount) throws IOException {
        word = pickWord(amount);
    }

    public String pickWord(int amount) throws IOException {
      File file = new File("src/words.txt");
      if (!file.exists()) {
          throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
      }
  
      try (Scanner scanner = new Scanner(file)) {
          String word = null;
          for (int i = 0; i < amount; i++) {
              if (!scanner.hasNextLine()) {
                  throw new IllegalArgumentException("Not enough words in the file: " + file.getAbsolutePath());
              }
              word = scanner.nextLine();
          }
          return word;
      }
  }

    public String accessWord() {
        return word;
    }
}
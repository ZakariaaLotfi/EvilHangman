import java.io.IOException;

class Main {
    // public static String wordString() throws IOException{
    //     File file = new File("src/words.txt");
    //     if (!file.exists()) {
    //         throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
    //     }
    
    //     try (Scanner scanner = new Scanner(file)) {
    //         String word = null;
    //         int length = 8;
    //         String nword = null;
    //         for (int i = 0; i < 2048; i++) {
    //             if (!scanner.hasNextLine()) {
    //                 throw new IllegalArgumentException("Not enough words in the file: " + file.getAbsolutePath());
    //             }
    //             word = scanner.nextLine();
    //             if (word.length()<length){
    //                 length = word.length();
    //                 nword = word;
    //             }
    //         }
    //         return nword;
    //     }
    // }
    public static void main(String[] args) throws IOException, InterruptedException {
      Game game = new Game();
    }
  }
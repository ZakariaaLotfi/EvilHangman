public class Hangman {
    private int lives = 8;
    private String[] hangmanStages = {
        "   +---+\n" +
        "       |\n" +
        "       |\n" +
        "       |\n" +
        "       |\n" +
        "       |\n" +
        "=========",

        "   +---+\n" +
        "   |   |\n" +
        "       |\n" +
        "       |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "       |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "   |   |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "  /|   |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "  /|\\  |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "  /|\\  |\n" +
        "  /    |\n" +
        "       |\n" +
        "=========",
        
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "  /|\\  |\n" +
        "  / \\  |\n" +
        "       |\n" +
        "========="
    };

    public String newStage(){
        lives--;
        return hangmanStages[7-lives];
    }

    public int livesAccessor(){
        return lives;
    }
    public String[] stagesAccessor(){
        return hangmanStages;
    }
}

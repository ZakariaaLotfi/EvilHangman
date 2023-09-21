public class Hangman {
    private int lives = 7;
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

    public int livesAccessor(){
        return lives;
    }
    public String[] stagesAccessor(){
        return hangmanStages;
    }
}

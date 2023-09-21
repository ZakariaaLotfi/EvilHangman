public class Hangman {
    private int lives = 7;
    private int stage = 0;
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
        stage-=1;
        return hangmanStages[stage];
    }

    public int livesAccessor(){
        return lives;
    }
    public String[] stagesAccessor(){
        return hangmanStages;
    }
}

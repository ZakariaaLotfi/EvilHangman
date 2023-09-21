import java.security.SecureRandom;
public class RandomNum {
    private int number;

    public RandomNum(){
        number = randomNumPicker();
    }
    public int randomNumPicker(){
        SecureRandom random = new SecureRandom();
        number = random.nextInt(2048);
        return number;
    }

    public int accessor() {
        return number;
    }
}
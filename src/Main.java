import com.sleepycat.je.Environment;

public class Main {
    public static void main(String[] args){
        ActiveEnviornment ae = new ActiveEnviornment();
        ae.setUpActiveEnviornment("testEnv2", true);

    }
}

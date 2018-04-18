import com.sleepycat.je.Environment;

public class Main {
    public static void main(String[] args){

        Controller controller = new Controller();
        try
        {
            controller.parseSQL("CREATE DATABASE TestEnv3");
        }
        catch (InvalidSQLCommandException isqlce)
        {
            isqlce.printStackTrace();
            System.out.println(isqlce.msg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

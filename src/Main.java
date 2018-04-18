import com.sleepycat.je.Environment;

public class Main {
    public static void main(String[] args){

        Controller controller = new Controller();
        try
        {
            controller.parseSQL("use TestEnv2");
            System.out.println("Elso command vege");
            controller.parseSQL("Create Table Table1");
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

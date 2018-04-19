import com.sleepycat.je.Environment;

public class Main {
    public static void main(String[] args){

        Controller controller = new Controller();
        try
        {
            controller.parseSQL("drop TestEnv2");
            //System.out.println("Elso command vege");
            //controller.parseSQL("Create Table Table1");
            //controller.parseSQL("Create Table Table2");
            //controller.parseSQL("insert into Table1 values alma");
            controller.parseSQL("create database szia");

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

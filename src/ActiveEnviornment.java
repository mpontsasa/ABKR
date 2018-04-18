import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import java.io.File;

public class ActiveEnviornment {

    private Environment enviornment = null;
    private String name = null;


    public void setUpActiveEnviornment(String name, boolean allowCreate)
    {
        this.name = name;

        if(allowCreate){
            File folder = new File(Finals.ENVIORNMENT_PATH + name);

            if (!folder.exists()) {
                folder.mkdir();
            }
        }

        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(allowCreate);
        enviornment = new Environment(new File(Finals.ENVIORNMENT_PATH + name), envConfig);
    }

    public  boolean isSetUp(){
        return (enviornment == null);
    }

    public void createDB(String name)
    {
        Database myDatabase = null;
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);
        myDatabase = enviornment.openDatabase(null, name, dbConfig);
        myDatabase.close();
    }

    public void deleteDB(String name)
    {
        enviornment.removeDatabase(null, name);
    }
}

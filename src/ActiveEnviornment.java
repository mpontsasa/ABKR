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
}

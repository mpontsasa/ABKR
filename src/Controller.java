import java.lang.module.InvalidModuleDescriptorException;

public class Controller {

    private UserLayer userLayer;
    private SQLDatabaseStructure sqlDatabaseStructure;  //structure of active database
    private ActiveEnviornment activeEnviornment = new ActiveEnviornment();


    public Controller() {

    }

    public void parseSQL(String commandSQL) throws InvalidModuleDescriptorException{

        String words[] = commandSQL.split(" ");

        if(words.length < 2)
        {
            throw new InvalidModuleDescriptorException("Too short command.");
        }

        switch(words[0])
        {
            case "USE":
                if (words.length != 2){
                    throw new InvalidModuleDescriptorException("Too much words after USE.");
                }
                activeEnviornment.setUpActiveEnviornment(words[2],false);

                break;
            case "CREATE":

        }

    }

    public void createDatabase(String databaseName){


    }

    public void dropDatabase(){

    }

    public void createTable(){

        //TableStructure tableStructure = new TableStructure()
    }

    public void dropTable(){

    }

    public String getActiveDatabase() {
        return activeDatabase;
    }

    public void setActiveDatabase(String activeDatabase) {
        this.activeDatabase = activeDatabase;
    }
}

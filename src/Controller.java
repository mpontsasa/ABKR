import java.lang.module.InvalidModuleDescriptorException;

public class Controller {

    private UserLayer userLayer = null;
    private SQLDatabaseStructure sqlDatabaseStructure = null;  //structure of active database
    private ActiveEnviornment activeEnviornment = new ActiveEnviornment();

    public Controller() {

    }

    public void parseSQL(String commandSQL) throws Exception{

        String words[] = commandSQL.split(" ");

        if(words.length < 2)
        {
            throw new InvalidSQLCommandException("Too short command.");
        }

        switch(words[0])
        {
            case "USE":
                useDatabaseCommand(words);
                break;
            case "CREATE":


        }
    }

    public void useDatabaseCommand(String[] words) throws InvalidSQLCommandException{
        if (words.length != 2){
            throw new InvalidSQLCommandException("Too much words after USE.");
        }
        try{
            activeEnviornment.setUpActiveEnviornment(words[1],false);

            sqlDatabaseStructure = new SQLDatabaseStructure(words[1]);
            JsonIOMaster io = new JsonIOMaster(sqlDatabaseStructure);
            io.readDBFromFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createDatabaseCommand(String databaseName){


    }

    public void createTableCommand(){

        //TableStructure tableStructure = new TableStructure()
    }

    public void dropDatabaseCommand(){

    }

    public void dropTableCommand(){

    }
}

import java.io.File;
import java.lang.module.InvalidModuleDescriptorException;
import java.nio.file.Files;
import java.nio.file.Path;

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

        switch(words[0].toUpperCase())
        {
            case "USE":
                useDatabaseCommand(words);
                break;
            case "CREATE":
                if (words[1].equalsIgnoreCase("DATABASE")){
                    createDatabaseCommand(words);
                }
                else if(words[1].equalsIgnoreCase("TABLE")){
                    createTableCommand(words);
                }


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
            //io.readDBFromFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createDatabaseCommand(String[] words)  throws Exception {
        if (words.length != 3){
            throw new InvalidSQLCommandException("Too much words after USE.");
        }

        if (new File(Finals.ENVIORNMENT_PATH + words[2]).exists()) {
            throw new InvalidSQLCommandException("Database " + words[2] + " alredy exists");
        }

        try{
            activeEnviornment.setUpActiveEnviornment(words[2],true);

            //create structure!!!!!!!!!!!!!!!!++++++
            //sqlDatabaseStructure = new SQLDatabaseStructure(words[1]);
            //JsonIOMaster io = new JsonIOMaster(sqlDatabaseStructure);
            //io.readDBFromFile();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void createTableCommand(String[] words){

        //TableStructure tableStructure = new TableStructure()
    }

    public void dropDatabaseCommand(){
        //teszt komment gitnek
        //tk2
        //pull teszt karis
    }

    public void dropTableCommand(){

    }
}

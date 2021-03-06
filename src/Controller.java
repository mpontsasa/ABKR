import java.io.File;
import java.lang.module.InvalidModuleDescriptorException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

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
                break;
            case "DROP":
                if (words[1].equalsIgnoreCase("DATABASE")){
                    dropDatabaseCommand(words);
                }
                else if(words[1].equalsIgnoreCase("TABLE")){
                    dropTableCommand(words);
                }
                break;
            case "INSERT":
                insertIntoCommand(words);
                break;
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

    public void createDatabaseCommand(String[] words) throws Exception {
        if (words.length != 3){
            throw new InvalidSQLCommandException("Insuficient length.");
        }

        if (new File(Finals.ENVIORNMENT_PATH + words[2]).exists()) {
            throw new InvalidSQLCommandException("Database " + words[2] + " alredy exists");
        }

        try{
            activeEnviornment.setUpActiveEnviornment(words[2],true);

            //create structure!!!!!!!!!!!!!!!!++++++
            SQLDatabaseStructure databaseStructure = new SQLDatabaseStructure(words[2]);
            databaseStructure.toJson();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTableCommand(String[] words)throws Exception{

        if (!activeEnviornment.isSetUp())
        {
            throw new InvalidSQLCommandException("No database selected");
        }

        activeEnviornment.createDB(words[2]);
        /// MEG KELL: szerkezet letrehozasa
        //TableStructure tableStructure = new TableStructure()
    }

    public void dropDatabaseCommand(String[] words)throws Exception{
        if (words.length != 3){
            throw new InvalidSQLCommandException("Insuficient length.");
        }

        if (!deleteDirectory(new File(Finals.ENVIORNMENT_PATH + words[2])))
        {
            throw new InvalidSQLCommandException("Cant delete Database(probobly dosent exist)");
        }

        activeEnviornment = null;

        ///MEG KELL: szerkezet torlese
    }

    public void dropTableCommand(String[] words)throws Exception{
        if (words.length != 3){
            throw new InvalidSQLCommandException("Insuficient length.");
        }

        if (!activeEnviornment.isSetUp())
        {
            throw new InvalidSQLCommandException("No database selected");
        }

        activeEnviornment.deleteDB(words[2]);
    }

    public void insertIntoCommand(String[] words)throws Exception{

        if (!activeEnviornment.isSetUp())
        {
            throw new InvalidSQLCommandException("No database selected");
        }

        if (!words[1].equalsIgnoreCase("INTO") || !words[3].equalsIgnoreCase("VALUES"))
        {
            throw new InvalidSQLCommandException("Unknown command format.");
        }

        activeEnviornment.insertIntoDB(words[2], Arrays.copyOfRange(words, 4, words.length));
    }

    public static boolean deleteDirectory(File directory) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }

    public SQLDatabaseStructure getSqlDatabaseStructure() {
        return sqlDatabaseStructure;
    }
}

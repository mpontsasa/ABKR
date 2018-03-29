public class Controller {

    private UserLayer userLayer;
    private String activeDatabase;//what database is currently in use?


    public Controller() {

        activeDatabase = "master";
    }


    public void createDatabase(String databaseName){


    }

    public void dropDatabase(){

    }

    public void createTable(){

        TableStructure tableStructure = new TableStructure()
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

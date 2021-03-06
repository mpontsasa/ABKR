import javafx.scene.control.Tab;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SQLDatabaseStructure {

    private ArrayList<TableStructure> tables;
    private String name;

    public SQLDatabaseStructure(String name) {
        this.name = name;
        tables = new ArrayList<>();
    }

    public void addTable(TableStructure t){
        tables.add(t);
    }

    public void deleteTable(TableStructure t){
        tables.remove(t);
    }

    public void toJson() throws IOException{

        FileWriter fw = new FileWriter(name + ".json");

        JSONObject res = new JSONObject();
        res.put(Finals.JSON_DATABASE_NAME_KEY, name);
        JSONArray JSONTables = new JSONArray();
        for(TableStructure table:tables){
            JSONTables.put(table.toJson());
        }
        res.put(Finals.JSON_DATABASE_TABLES_KEY, JSONTables);

        fw.write(res.toString());
        fw.flush();
        fw.close();
    }


    @Override
    public String toString(){
        return name + "Ez egy adatbazis!";
    }

    public String getName() {
        return name;
    }
}

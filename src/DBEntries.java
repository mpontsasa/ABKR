import java.util.ArrayList;
import java.util.HashMap;

public class DBEntries {

    //used to store a table's data
    private HashMap<String,String> records;


    public DBEntries() {
        records = new HashMap<>();
    }

    public void addRecord(String key, String value) throws ExistingKeyException{
        if(records.containsKey(key)){
            throw new ExistingKeyException();
        }
        records.put(key,value);
    }

    public String get(String key){
        if(!records.containsKey(key)){
            return null;
        }
        return records.get(key);
    }



}

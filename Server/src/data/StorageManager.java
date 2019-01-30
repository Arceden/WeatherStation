package data;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageManager {

    //Multidimensional lists
    private static HashMap<Integer, ArrayList<StorageRecord>> records;

    public StorageManager(){
        records = new HashMap<Integer, ArrayList<StorageRecord>>(8000);
    }

    /**
     * Insert the storageRecord into the arraylist of the station number
     * @param storageRecord - Record of measurements
     */
    public static void add(StorageRecord storageRecord){

        //If the the key of the station is not known, go to catch
        try {
            (records.get(storageRecord.getStn())).add(storageRecord);
        } catch (NullPointerException e){
            records.put(storageRecord.getStn(), new ArrayList<StorageRecord>(10));
            (records.get(storageRecord.getStn())).add(storageRecord);
        }

        if((records.get(storageRecord.getStn())).size()>9){
            System.err.println(storageRecord.getStn()+" reached 10 records!");
        }

    }

}

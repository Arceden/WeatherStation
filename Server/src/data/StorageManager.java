package data;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StorageManager {

    //Multidimensional lists
    private static HashMap<Integer, ArrayList<StorageRecord>> records;
    private static final int MAX_RECORDS = 10;

    public StorageManager(){
        records = new HashMap<Integer, ArrayList<StorageRecord>>(8000);
    }

    /**
     * Insert the storageRecord into the arraylist of the station number
     * @param storageRecord - Record of measurements
     */
    public static void add(StorageRecord storageRecord){

        int stn = storageRecord.getStn();

        //If the the key of the station is not known, go to catch
        try {
            (records.get(stn)).add(storageRecord);
        } catch (NullPointerException e){
            records.put(stn, new ArrayList<StorageRecord>(10));
            (records.get(stn)).add(storageRecord);
        }

        //Check if the station has 10 records available
        if((records.get(stn)).size()==10)
            finalizeNumbers(stn);

    }

    /**
     * Preform a check to check if all numbers are available to generate a realistic number
     * @param stn - Station Number (Indentifier)
     */
    private static void finalizeNumbers(int stn){

        StorageRecord result = new StorageRecord();
        ArrayList<StorageRecord> items = records.get(stn);

        //These values cant be recalculated easly
        //They have to be checked for errors though
        result.setStn(stn);
        result.setFrshht(items.get(0).getFrshht());
        result.setTimestamp(items.get(0).getDate(), items.get(0).getTime());

        //Sum variables
        short wnddir=0;
        float
                temp=0,
                dewp=0,
                stp=0,
                slp=0,
                visib=0,
                wdsp=0,
                prcp=0,
                sndp=0,
                cldc=0;

        //Sum the rest
        //TODO: Check if this record had an error
        for(StorageRecord record: items){
            wnddir+=record.getWnddir();
            temp+=record.getTemp();
            dewp+=record.getDewp();
            stp+=record.getStp();
            slp+=record.getSlp();
            visib+=record.getVisib();
            wdsp+=record.getWdsp();
            prcp+=record.getPrcp();
            sndp+=record.getSndp();
            cldc+=record.getCldc();
        }

        //Calculate realistic number
        result.setTemp(temp/=MAX_RECORDS);
        result.setDewp(dewp/=MAX_RECORDS);
        result.setStp(stp/=MAX_RECORDS);
        result.setSlp(slp/=MAX_RECORDS);
        result.setVisib(visib/=MAX_RECORDS);
        result.setWdsp(wdsp/=MAX_RECORDS);
        result.setPrcp(prcp/=MAX_RECORDS);
        result.setSndp(sndp/=MAX_RECORDS);
        result.setCldc(cldc/=MAX_RECORDS);
        result.setWnddir(wnddir/=MAX_RECORDS);

        //Send it to the VM
//        saveToFile(result);


    }

    /**
     * For debugging purposes only
     * @param sr - StorageRecord. Contains all the details about the measurement
     */
    private static void saveToFile(StorageRecord sr){

        try {
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                            //Weather Station Data Frame
                            new FileOutputStream("test_data/"+sr.getStn()+".wsd")

                    )
            );

            try {

                /*
                Station 0:999999 INT
                --ID 1:8000 SHORT
                Date 07/02/2019/00.00:12/31/2100/23.59 DATETIME
                Temp -100,0:100,0 SHORT
                Dauwpunt -100,0:100,0 SHORT
                STP 0:9999,9 SHORT
                SLP 0:9999,9 SHORT
                VISIB 0:999,9 SHORT
                WDSP 0:500,0 SHORT
                PRCP: 0:500,0 SHORT
                SNDP: 0:500,0 SHORT
                FRSHTT 0:63 BYTE
                CLDC 0:99,9 SHORT
                WNDDIR 0:359 SHORT
                 */
                //Store all data
                out.writeInt(sr.getStn());
                out.writeLong(sr.getTimestamp());
                out.writeShort((short)(sr.getTemp()*10));
                out.writeShort((short)(sr.getDewp()*10));
                out.writeShort((short)(sr.getStp()*10));
                out.writeShort((short)(sr.getSlp()*10));
                out.writeShort((short)(sr.getVisib()*10));
                out.writeShort((short)(sr.getWdsp()*10));
                out.writeShort((short)(sr.getPrcp()*10));
                out.writeShort((short)(sr.getSndp()*10));
                out.writeByte(sr.getFrshht());
                out.writeShort((short)(sr.getCldc()*10));
                out.writeShort(sr.getWnddir());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

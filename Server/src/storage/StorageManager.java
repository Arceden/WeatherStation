package storage;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class StorageManager {

    private int row_id;
    private int stn;
    private long timestamp;
    private float temp;
    private float dewp;
    private float stp;
    private float slp;
    private float visib;
    private float wdsp;
    private float prcp;
    private float sndp;
    private byte frshht;
    private float cldc;
    private short wnddir;

    private String version;
    String target_directory;
    String target_filename;
    int frame_size;

    StorageManager(){
        loadSettings();
    }

    /**
     * Load WSD settings
     */
    private void loadSettings(){

        //Load the properties file
        Properties prop = new Properties();

        //try {
            //prop.load(new FileInputStream("/wsd.properties"));
            this.version = "0.1";
            this.target_directory = "./data/";
            this.target_filename = "data.wsd";
            this.frame_size = 37;

       // } catch (IOException e){
//            System.err.println("Could not load the WeatherDataFrame properties!");
//            e.printStackTrace();
//            System.exit(1);
//        }

        //Display the current WeatherStationData Frame version
//        System.err.println("StorageManager version: "+this.version);
//        System.err.println("Current save location: "+this.target_directory+this.target_filename);

    }

    /**
     * Save frame to file
     */
    void saveDataFrame(){

        try {
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                            //Weather Station Data Frame
                            new FileOutputStream(this.target_directory+this.target_filename, true)

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
                out.writeInt(getStn());
                out.writeLong(getTimestamp());
                out.writeShort((short)(getTemp()*10));
                out.writeShort((short)(getDewp()*10));
                out.writeShort((short)(getStp()*10));
                out.writeShort((short)(getSlp()*10));
                out.writeShort((short)(getVisib()*10));
                out.writeShort((short)(getWdsp()*10));
                out.writeShort((short)(getPrcp()*10));
                out.writeShort((short)(getSndp()*10));
                out.writeByte(getFrshht());
                out.writeShort((short)(getCldc()*10));
                out.writeShort(getWnddir());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Convert the Date and Time into a single long
     * @param date in the following format: yyyy-MM-dd
     * @param time in the following format: HH:mm:ss
     * @return long timestamp
     */
    long getTimestamp(String date, String time){

        //Format the following to a UNIX timestamp in a long datatype 2009-09-13 15:59:46
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = format.parse(date+" "+time);
            return d.getTime();
        } catch (ParseException e){
            e.printStackTrace();
        }

        return 0;

    }

    /**
     * GETTERS AND SETTERS
     */
    public int getRow_id() {
        return row_id;
    }

    public int getStn() {
        return stn;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getTemp() {
        return temp;
    }

    public float getDewp() {
        return dewp;
    }

    public float getStp() {
        return stp;
    }

    public float getSlp() {
        return slp;
    }

    public float getVisib() {
        return visib;
    }

    public float getWdsp() {
        return wdsp;
    }

    public float getPrcp() {
        return prcp;
    }

    public float getSndp() {
        return sndp;
    }

    public byte getFrshht() {
        return frshht;
    }

    public float getCldc() {
        return cldc;
    }

    public short getWnddir() {
        return wnddir;
    }

    public void setRow_id(int row_id) {
        this.row_id = row_id;
    }

    public void setStn(int stn) {
        this.stn = stn;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setDewp(float dewp) {
        this.dewp = dewp;
    }

    public void setStp(float stp) {
        this.stp = stp;
    }

    public void setSlp(float slp) {
        this.slp = slp;
    }

    public void setVisib(float visib) {
        this.visib = visib;
    }

    public void setWdsp(float wdsp) {
        this.wdsp = wdsp;
    }

    public void setPrcp(float prcp) {
        this.prcp = prcp;
    }

    public void setSndp(float sndp) {
        this.sndp = sndp;
    }

    public void setFrshht(byte frshht) {
        this.frshht = frshht;
    }

    public void setCldc(float cldc) {
        this.cldc = cldc;
    }

    public void setWnddir(short wnddir) {
        this.wnddir = wnddir;
    }
}
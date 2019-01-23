package storage;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StorageConverter {

    private int stn;
    private String date;
    private String time;
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

    public StorageConverter(){

        float sndp = -999.99f;
        String date = "2009-09-13";
        String time = "15:59:46";
        this.timestamp = this.getTimestamp(date, time);

    }

    /**
     * Process all the details and store it in a file
     */
    public void process(){

        this.getTimestamp(this.date, this.time);

        try {
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("./bin_output")
                    )
            );

            try {
                //Store all data
                out.writeInt(this.stn);
                out.writeLong(this.timestamp);
                out.writeFloat(this.temp);
                out.writeFloat(this.dewp);
                out.writeFloat(this.stp);
                out.writeFloat(this.slp);
                out.writeFloat(this.visib);
                out.writeFloat(this.wdsp);
                out.writeFloat(this.prcp);
                out.writeFloat(this.sndp);
                out.writeByte(this.frshht);
                out.writeFloat(this.cldc);
                out.writeShort(this.wnddir);
                out.flush();
            } catch (IOException e){
                e.printStackTrace();
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Convert the Date and Time into a single long
     * @param date in the following format: yyyy-MM-dd
     * @param time in the following format: HH:mm:ss
     * @return long timestamp
     */
    private long getTimestamp(String date, String time){

        date = date + " " + time;
        System.out.println("New format: "+date);

        //Format the following to a UNIX timestamp in a long datatype 2009-09-13 15:59:46
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = format.parse(date);
            return d.getTime();
        } catch (ParseException e){
            e.printStackTrace();
        }

        return 0;

    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
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

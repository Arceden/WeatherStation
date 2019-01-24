package storage;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class StorageConverter extends StorageManager {

    private String date;
    private String time;

    public StorageConverter() {

        setRow_id(25403);

    }

    /**
     * Process all the details and store it in a file
     */
    public void process() {

        setTimestamp(getTimestamp(getDate(), getTime()));
        saveDataFrame();

    }

    /**
     * (String) Float to Short
     */
    public short floatStringToShort(String value){
        return floatStringToShort(value,0);
    }
    public short floatStringToShort(String value, int decimals){
        float tmp = Float.parseFloat(value);
        for(int i=0;i<decimals;i++)tmp*=10;
        value = (tmp+"").replace(".0","");
        return Short.parseShort(value);
    }

    /**
     * Set parsers
     */
    public void setRow_id(String row_id) {
        setRow_id(Integer.parseInt(row_id));
    }

    public void setStn(String stn) {
        setStn(Integer.parseInt(stn));
    }

    public void setTemp(String temp) {
        setTemp(Float.parseFloat(temp));
    }

    public void setDewp(String dewp) {
        setDewp(Float.parseFloat(dewp));
    }

    public void setStp(String stp) {
        setStp(Float.parseFloat(stp));
    }

    public void setSlp(String slp) {
        setSlp(Float.parseFloat(slp));
    }

    public void setVisib(String visib) {
        setVisib(Float.parseFloat(visib));
    }

    public void setWdsp(String wdsp) {
        setWdsp(Float.parseFloat(wdsp));
    }

    public void setPrcp(String prcp) {
        setPrcp(Float.parseFloat(prcp));
    }

    public void setSndp(String sndp) {
        setSndp(Float.parseFloat(sndp));
    }

    public void setFrshht(String frshht) {
        setFrshht(Byte.parseByte(frshht,2));
    }

    public void setCldc(String cldc) {
        setCldc(floatStringToShort(cldc,1));
    }

    public void setWnddir(String wnddir) {
        setWnddir(Short.parseShort(wnddir));
    }


    private String getDate(){
        return this.date;
    }
    private String getTime(){
        return this.time;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setTime(String time){
        this.time=time;
    }

}
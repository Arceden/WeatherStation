package data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StorageRecord implements Serializable {

    private int stn;
    private byte frshht;
    private short wnddir;
    private long timestamp;
    private String date, time;
    private float temp, dewp, stp, slp, visib, wdsp, prcp, sndp, cldc;

    private String[] errors = new String[11];
    private int error_pos;

    //Add errors to the errorlist
    public void addError(String value) {
        this.errors[this.error_pos] = value;
        this.error_pos++;
    }
    //Method to call the errorlist
    public String[] errorlist () {
        return errors;
    }

        ////////////////////
        //  GETTERS
        ////////////////////


        public String getDate () {
            return date;
        }

        public String getTime () {
            return time;
        }

        public byte getFrshht () {
            return frshht;
        }

        public short getWnddir () {
            return wnddir;
        }

        public long getTimestamp () {
            return timestamp;
        }

        public int getStn () {
            return stn;
        }

        public float getTemp () {
            return temp;
        }

        public float getDewp () {
            return dewp;
        }

        public float getStp () {
            return stp;
        }

        public float getSlp () {
            return slp;
        }

        public float getVisib () {
            return visib;
        }

        public float getWdsp () {
            return wdsp;
        }

        public float getPrcp () {
            return prcp;
        }

        public float getSndp () {
            return sndp;
        }

        public float getCldc () {
            return cldc;
        }


        ////////////////////
        //  SETTERS
        ////////////////////


        public void setDate (String date){
            this.date = date;
        }

        public void setTime (String time){
            this.time = time;
        }

        public void setFrshht ( byte frshht){
            this.frshht = frshht;
        }

        public void setWnddir ( short wnddir){
            this.wnddir = wnddir;
        }

        public void setTimestamp ( long timestamp){
            this.timestamp = timestamp;
        }

        public void setStn ( int stn){
            this.stn = stn;
        }

        public void setTemp ( float temp){
            this.temp = temp;
        }

        public void setDewp ( float dewp){
            this.dewp = dewp;
        }

        public void setStp ( float stp){
            this.stp = stp;
        }

        public void setSlp ( float slp){
            this.slp = slp;
        }

        public void setVisib ( float visib){
            this.visib = visib;
        }

        public void setWdsp ( float wdsp){
            this.wdsp = wdsp;
        }

        public void setPrcp ( float prcp){
            this.prcp = prcp;
        }

        public void setSndp ( float sndp){
            this.sndp = sndp;
        }

        public void setCldc ( float cldc){
            this.cldc = cldc;
        }


        ////////////////////
        // Parsing SETTERS
        ////////////////////

        public void setFrshht (String frshht){
            if (IsError(frshht)) {
                addError(frshht);
            } else {
                setFrshht(
                        Byte.parseByte(frshht, 2)
                );
            }
        }

        public void setWnddir (String wnddir){
            if (IsError(wnddir)) {
                addError(wnddir);
            } else {
                setWnddir(
                        Short.parseShort(wnddir)
                );
            }
        }

        public void setStn (String stn){
            setStn(
                    Integer.parseInt(stn)
            );
        }

        public void setTemp (String temp){
            if (IsError(temp)) {
                addError(temp);
            } else {
                setTemp(
                        Float.parseFloat(temp)
                );
            }
        }

        public void setDewp (String dewp){
            if (IsError(dewp)) {
                addError(dewp);
            } else {
                setDewp(
                        Float.parseFloat(dewp)
                );
            }
        }

        public void setStp (String stp){
            if (IsError(stp)) {
                addError(stp);
            } else {
                setStp(
                        Float.parseFloat(stp)
                );
            }
        }

        public void setSlp (String slp){
            if (IsError(slp)) {
                addError(slp);
            } else {
                setSlp(
                        Float.parseFloat(slp)
                );
            }
        }

        public void setVisib (String visib){
            if (IsError(visib)) {
                addError(visib);
            } else {
                setVisib(
                        Float.parseFloat(visib)
                );
            }
        }

        public void setWdsp (String wdsp){
            if (IsError(wdsp)) {
                addError(wdsp);
            } else {
                setWdsp(
                        Float.parseFloat(wdsp)
                );
            }
        }

        public void setPrcp (String prcp){
            if (IsError(prcp)) {
                addError(prcp);
            } else {
                setPrcp(
                        Float.parseFloat(prcp)
                );
            }
        }

        public void setSndp (String sndp){
            if (IsError(sndp)) {
                addError(sndp);
            } else {
                setSndp(
                        Float.parseFloat(sndp)
                );
            }
        }

        public void setCldc (String cldc){
            if (IsError(cldc)) {
                addError(cldc);
            } else {
                setCldc(
                        Float.parseFloat(cldc)
                );
            }
        }

        public void setTimestamp (String date, String time){

            //Format the following to a UNIX timestamp in a long datatype 2009-09-13 15:59:46
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date d = format.parse(date + " " + time);
                setTimestamp(d.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        //Check if value is invalid
        public boolean IsError (String value){
            if (value == null || value == "0") {
                return true;
            }
            return false;
        }
    }

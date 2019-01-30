package data;

public class StorageRecord {

    private int stn;
    private byte frshht;
    private short wnddir;
    private long timestamp;
    private String date, time;
    private float temp, dewp, stp, slp, visib, wdsp, prcp, sndp, cldc;

    private int error_sum;

    public StorageRecord(){
        error_sum=0;
    }

    public void addError(){
        this.error_sum++;
    }

    public int errors(){
        return this.error_sum;
    }

    ////////////////////
    //  GETTERS
    ////////////////////

    public byte getFrshht() {
        return frshht;
    }

    public short getWnddir() {
        return wnddir;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getStn() {
        return stn;
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

    public float getCldc() {
        return cldc;
    }


    ////////////////////
    //  SETTERS
    ////////////////////


    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFrshht(byte frshht) {
        this.frshht = frshht;
    }

    public void setWnddir(short wnddir) {
        this.wnddir = wnddir;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setStn(int stn) {
        this.stn = stn;
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

    public void setCldc(float cldc) {
        this.cldc = cldc;
    }


    ////////////////////
    // Parsing SETTERS
    ////////////////////

    public void setFrshht(String frshht) {
        setFrshht(
                Byte.parseByte(frshht,2)
        );
    }

    public void setWnddir(String wnddir) {
        setWnddir(
                Short.parseShort(wnddir)
        );
    }

    public void setStn(String stn) {
        setStn(
                Integer.parseInt(stn)
        );
    }

    public void setTemp(String temp) {
        setTemp(
                Float.parseFloat(temp)
        );
    }

    public void setDewp(String dewp) {
        setDewp(
                Float.parseFloat(dewp)
        );
    }

    public void setStp(String stp) {
        setStp(
                Float.parseFloat(stp)
        );
    }

    public void setSlp(String slp) {
        setSlp(
                Float.parseFloat(slp)
        );
    }

    public void setVisib(String visib) {
        setVisib(
                Float.parseFloat(visib)
        );
    }

    public void setWdsp(String wdsp) {
        setWdsp(
                Float.parseFloat(wdsp)
        );
    }

    public void setPrcp(String prcp) {
        setPrcp(
                Float.parseFloat(prcp)
        );
    }

    public void setSndp(String sndp) {
        setSndp(
                Float.parseFloat(sndp)
        );
    }

    public void setCldc(String cldc) {
        setCldc(
                Float.parseFloat(cldc)
        );
    }

}

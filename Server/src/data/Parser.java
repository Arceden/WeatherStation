package data;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parser {

    private final ExecutorService XMLProcessingPool;
    private final int
            stn = 18,
            date = 38,
            time = 63,
            temp = 86,
            dewp = 104,
            stp = 121,
            slp = 141,
            visib = 161,
            wdsp = 181,
            prcp = 199,
            sndp = 219,
            frshtt = 239,
            cldc = 262,
            wnddir = 283;


    /**
     * Initlialize the SAX handler which converts XML into seperate Strings
     */
    public Parser(){
        this.XMLProcessingPool = Executors.newFixedThreadPool(4);
    }

    /**
     * Send a String with XML to the parse handler
     * @param xml - String
     */
    public void Parse(String xml){
        XMLProcessingPool.submit(new ParseTask(xml));
    }

    private String getValue(String xml, int position){
        return getValue(xml, position, 10);
    }
    private String getValue(String xml, int position, int length){
        String value = xml.substring(position,position+length);
        value = (value.split("<"))[0];
        value = (value.split(">"))[1];
        return value;
    }

    private class ParseTask implements Runnable {

        private String xml;

        private ParseTask(String xml){
            this.xml=xml;
        }

        @Override
        public void run(){

            boolean running=true;
            xml = xml.substring(35);
            String measurements[] = xml.split("</MEASUREMENT>");

            for(int i=0;i<measurements.length;i++){
                StorageRecord sr = new StorageRecord();
                String row = measurements[i];

                sr.setStn( getValue(row, stn) );
                sr.setDate( getValue(row, date));
                sr.setTime( getValue(row, time));
                sr.setDewp( getValue(row, dewp));
                sr.setStp( getValue(row, stp));
                sr.setSlp( getValue(row, slp));
                sr.setVisib( getValue(row, visib));
                sr.setWdsp( getValue(row, wdsp));
                sr.setPrcp( getValue(row, prcp));
                sr.setSndp( getValue(row, sndp));
                sr.setFrshht( getValue(row, frshtt));
                sr.setCldc( getValue(row, cldc));
                sr.setWnddir( getValue(row, wnddir));

                StorageManager.add(sr);

            }

        }

    }

}

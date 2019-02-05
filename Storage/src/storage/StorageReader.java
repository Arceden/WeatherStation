package storage;

import data.DataFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.MappedByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StorageReader {

    private final int FRAMESIZE = 35;

    private int records;
    private MappedByteBuffer buffer;

    public StorageReader(){
        loadValues();
    }

    private void loadValues(){
        this.buffer = DataFrame.loadData();
        this.records = buffer.remaining()/FRAMESIZE;
    }

    public void keepUpToDate(){
        query("big");
    }

    public void query(String type) {
        switch (type){
            case "all":
                bigboi();
                smolboi();
                break;
            case "big":
                bigboi();
                break;
            case "smol":
                smolboi();
        }
    }

    private void smolboi(){
        System.out.println("Generating smolboi");
    }

    private void bigboi(){

        //Request ALL data (as .csv)
        System.out.println("Generating bigboi. Amount of records: "+records);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        try {
            CSV csv = new CSV();

            int position = 0;
            for(int i=0;i<records;i++){
                position=(i*FRAMESIZE);

                //Get the correct date/time
                long timestamp=buffer.getLong(position+4);
                Date d = new Date(timestamp);

                int stn = buffer.getInt(position);
                String date = dateFormat.format(d);
                String time = timeFormat.format(d);
                float temp = (float)buffer.getShort(position+12)/10;
                float dewp = (float)buffer.getShort(position+14)/10;
                float stp = (float)buffer.getShort(position+16)/10;
                float slp = (float)buffer.getShort(position+18)/10;
                float visib = (float)buffer.getShort(position+20)/10;
                float wdsp = (float)buffer.getShort(position+22)/10;
                float prcp = buffer.getFloat(position+24);
                float sndp = (float)buffer.getShort(position+28)/10;
                byte frshht = buffer.get(position+30);
                float cldc = (float)buffer.getShort(position+31)/10;
                short wnddir = buffer.getShort(position+33);


                csv.newRow();
                csv.newValue(stn);
                csv.newValue(date);
                csv.newValue(time);
                csv.newValue(temp);
                csv.newValue(dewp);
                csv.newValue(stp);
                csv.newValue(slp);
                csv.newValue(visib);
                csv.newValue(wdsp);
                csv.newValue(prcp);
                csv.newValue(sndp);
                csv.newValue(frshht);
                csv.newValue(cldc);
                csv.newValue(wnddir);

                csv.endRow();

            }

            csv.save();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    private class CSV {

        private PrintWriter writer;
        private StringBuilder sb;

        CSV() throws FileNotFoundException {
            writer = new PrintWriter(new File("test.csv"));
            header();
        }

        private void header(){
            newRow();

            sb.append("Station");
            delimiter();
            sb.append("Date");
            delimiter();
            sb.append("Time");
            delimiter();
            sb.append("Temperature");
            delimiter();
            sb.append("Dew Point");
            delimiter();
            sb.append("Air Pressure Station Level");
            delimiter();
            sb.append("Air Pressure Sea Level");
            delimiter();
            sb.append("Visibility");
            delimiter();
            sb.append("Wind Speed");
            delimiter();
            sb.append("Rainfall");
            delimiter();
            sb.append("Snowfall");
            delimiter();
            sb.append("Status Codes");
            delimiter();
            sb.append("Cloud Cover");
            delimiter();
            sb.append("Wind Direction");
            delimiter();

            endRow();
        }

        void newValue(int value){
            sb.append(value);
            delimiter();
        }

        void newValue(String value){
            sb.append(value);
            delimiter();
        }

        void newValue(byte value){
            sb.append(value);
            delimiter();
        }

        void newValue(float value){
            sb.append(value);
            delimiter();
        }
        void newValue(short value){
            sb.append(value);
            delimiter();
        }
        void newValue(){
            delimiter();
        }

        void newRow(){
            sb = new StringBuilder();
        }

        void endRow(){
            sb.deleteCharAt(sb.length()-1);
            sb.append('\n');
            this.writer.write(sb.toString());
        }

        void delimiter(){
            sb.append(';');
        }

        void save(){
            writer.close();
            System.out.println("Finished the .csv file!");
        }

    }

}

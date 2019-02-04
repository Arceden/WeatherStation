package data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parser {

    private final ExecutorService XMLProcessingPool;

    /**
     * Initlialize the SAX handler which converts XML into seperate Strings
     */
    public Parser() {
        this.XMLProcessingPool = Executors.newFixedThreadPool(10);
    }

    /**
     * Send a String with XML to the parse handler
     * @param xml - String
     */
    public void Parse(String xml) {
        XMLProcessingPool.submit(new ParseTask(xml));
//        new ParseTask(xml);
    }

    private class ParseTask implements Runnable {

        private String xml;
        private int position;

        private ParseTask(String xml) {
            this.xml = xml;
            this.position=0;
        }

        @Override
        public void run() {

            StorageRecord record = new StorageRecord();
            String stn = getValue(xml,"stn");
            boolean yes = getBotswana(stn);

            if(yes) {
                record.setStn(stn);
                record.setDate(getValue(xml, "dat"));
                record.setTime(getValue(xml, "tim"));
                record.setTemp(getValue(xml, "tem"));
                record.setDewp(getValue(xml, "dew"));
                record.setStp(getValue(xml, "stp"));
                record.setSlp(getValue(xml, "slp"));
                record.setVisib(getValue(xml, "vis"));
                record.setWdsp(getValue(xml, "wds"));
                record.setPrcp(getValue(xml, "prc"));
                record.setSndp(getValue(xml, "snd"));
                record.setFrshht(getValue(xml, "frs"));
                record.setCldc(getValue(xml, "cld"));
                record.setWnddir(getValue(xml, "wnd"));

                StorageManager.add(record);
            }

        }

        /**
         * Find the XML thing
         * @param xml String
         * @param id String 3 characters long
         * @return value as String
         */
        private String getValue(String xml, String id){

            String value="";

            for(int i=this.position;i<xml.length();i++){

                String compare = ""+xml.charAt(i)+xml.charAt(i+1)+xml.charAt(i+2);
                if(compare.equalsIgnoreCase(id)){

                    String current;
                    boolean found = false;
                    while(i<xml.length()+3){
                        i++;
                        current = (xml.charAt(i)+"");

                        //Reached the end of the value
                        if(current.equalsIgnoreCase("<"))
                            break;

                        //Add the value if the value had been found
                        if(found)
                            value+=current;

                        //Reached the start of the value
                        if(current.equalsIgnoreCase(">"))
                            found=true;
                    }
                    this.position=i;
                    break;

                }

            }

            return value;

        }

        /**
         * Detect wether or not the station is from Botswana
         * @param id String, indentifier for station
         * @return boolean, true if from botswana
         */
        private boolean getBotswana(String id){
            //Create storage for Botswana station values
            String[] BotsValues = {"680240", "680260", "680290", "680320","680380", "680400", "680540", "682340", "682400", "682675"};
            //If Current value equals a Botswana station then set isbotswana to true
            for (int i = 0; i < BotsValues.length; i++) {
                if (id.equals (BotsValues[i])) {
                    //System.err.println(i);
                    return true;
                }
            }
            return false;
        }

    }
}
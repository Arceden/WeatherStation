package data;

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
    public Parser() {
        this.XMLProcessingPool = Executors.newFixedThreadPool(10);
    }

    /**
     * Send a String with XML to the parse handler
     *
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

            StorageRecord bo = new StorageRecord();
            StorageRecord sr = new StorageRecord();

            boolean yes = getBotswana(getValue(xml,"stn"));
            if(yes) {
                System.err.println(getValue(xml,"stn"));

                bo.setStn(getValue(xml, "stn"));
                bo.setDate(getValue(xml, "dat"));
                bo.setTime(getValue(xml, "tim"));
                bo.setTemp(getValue(xml, "tem"));
                bo.setDewp(getValue(xml, "dew"));
                bo.setStp(getValue(xml, "stp"));
                bo.setSlp(getValue(xml, "slp"));
                bo.setVisib(getValue(xml, "vis"));
                bo.setWdsp(getValue(xml, "wds"));
                bo.setPrcp(getValue(xml, "prc"));
                bo.setSndp(getValue(xml, "snd"));
                bo.setFrshht(getValue(xml, "frs"));
                bo.setCldc(getValue(xml, "cld"));
                bo.setWnddir(getValue(xml, "wnd"));

                StorageManager.add(bo);
            }
            else {
                sr.setStn(getValue(xml, "stn"));
                sr.setDate(getValue(xml, "dat"));
                sr.setTime(getValue(xml, "tim"));
                sr.setTemp(getValue(xml, "tem"));
                sr.setDewp(getValue(xml, "dew"));
                sr.setStp(getValue(xml, "stp"));
                sr.setSlp(getValue(xml, "slp"));
                sr.setVisib(getValue(xml, "vis"));
                sr.setWdsp(getValue(xml, "wds"));
                sr.setPrcp(getValue(xml, "prc"));
                sr.setSndp(getValue(xml, "snd"));
                sr.setFrshht(getValue(xml, "frs"));
                sr.setCldc(getValue(xml, "cld"));
                sr.setWnddir(getValue(xml, "wnd"));

                StorageManager.add(sr);
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

        private boolean getBotswana(String id){
            //Create storage for Botswana station values
            String[] BotsValues = {"680240", "680260", "680290", "680380", "680400", "680540", "682340", "682400", "682675"};
            //If Current value equals a Botswana station then set isbotswana to true
            for (int i = 0; i <= BotsValues.length; i++) {
                if (id.equals (BotsValues[i])) {
                        //System.err.println(i);
                        return true;
                }
            }
            return false;
        }




    }
}

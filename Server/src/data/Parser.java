package data;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parser {

    private SAXParser parser;
    private DefaultHandler handle;
    private StorageManager sm;
    private StorageRecord sr;

    private final ExecutorService SAXProcessingPool;

    /**
     * Initlialize the SAX handler which converts XML into seperate Strings
     */
    public Parser(){

        this.SAXProcessingPool = Executors.newFixedThreadPool(4);

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            this.parser = factory.newSAXParser();

            this.handle = new DefaultHandler() {
                boolean bstn = false,
                        bdate = false,
                        btime = false,
                        btemp = false,
                        bdewp = false,
                        bstp = false,
                        bslp = false,
                        bvisib = false,
                        bwdsp = false,
                        bprcp = false,
                        bsndp = false,
                        bfrshtt = false,
                        bcldc = false,
                        bwnddir = false;

                public void startElement(String uri, String localname, String qName, Attributes attributes) throws SAXException {
                    if (qName.equals("STN")) bstn = true;
                    if (qName.equals("DATE")) bdate = true;
                    if (qName.equals("TIME")) btime = true;
                    if (qName.equals("TEMP")) btemp = true;
                    if (qName.equals("DEWP")) bdewp = true;
                    if (qName.equals("STP")) bstp = true;
                    if (qName.equals("SLP")) bslp = true;
                    if (qName.equals("VISIB")) bvisib = true;
                    if (qName.equals("WDSP")) bwdsp = true;
                    if (qName.equals("PRCP")) bprcp = true;
                    if (qName.equals("SNDP")) bsndp = true;
                    if (qName.equals("FRSHTT")) bfrshtt = true;
                    if (qName.equals("CLDC")) bcldc = true;
                    if (qName.equals("WINDDIR")) bwnddir = true;
                }

                public void characters(char[] ch, int start, int length) throws SAXException {

                    try {
                        if (bstn) {
                            sr.setStn(new String(ch, start, length));
                            bstn = false;
                        }
                        if (bdate) {
                            sr.setDate(new String(ch, start, length));
                            bdate = false;
                        }
                        if (btime) {
                            sr.setTime(new String(ch, start, length));
                            btime = false;
                        }
                        if (btemp) {
                            sr.setTemp(new String(ch, start, length));
                            btemp = false;
                        }
                        if (bdewp) {
                            sr.setDewp(new String(ch, start, length));
                            bdewp = false;
                        }
                        if (bstp) {
                            sr.setStp(new String(ch, start, length));
                            bstp = false;
                        }
                        if (bslp) {
                            sr.setSlp(new String(ch, start, length));
                            bslp = false;
                        }
                        if (bvisib) {
                            sr.setVisib(new String(ch, start, length));
                            bvisib = false;
                        }
                        if (bwdsp) {
                            sr.setWdsp(new String(ch, start, length));
                            bwdsp = false;
                        }
                        if (bprcp) {
                            sr.setPrcp(new String(ch, start, length));
                            bprcp = false;
                        }
                        if (bsndp) {
                            sr.setSndp(new String(ch, start, length));
                            bsndp = false;
                        }
                        if (bfrshtt) {
                            sr.setFrshht(new String(ch, start, length));
                            bfrshtt = false;
                        }
                        if (bcldc) {
                            sr.setCldc(new String(ch, start, length));
                            bcldc = false;
                        }
                        if (bwnddir) {
                            sr.setWnddir(new String(ch, start, length));
                            bwnddir = false;
                        }
                    } catch (NumberFormatException e){
                        sr.addError();
                    }

                }
            };

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Send a String with XML to the parse handler
     * @param xml - String
     */
    public void Parse(String xml){

        SAXProcessingPool.submit(new ParseTask());

    }

    private class ParseTask implements Runnable {

        private ParseTask(){

        }

        @Override
        public void run(){

        }

    }

}

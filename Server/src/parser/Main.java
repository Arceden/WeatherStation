package parser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import storage.StorageWriter;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;


public class Main {

    public static void main(String[] args) {
        StorageWriter sc = new StorageWriter();
        for(int i=0;i<8000;i++)new Main().Go(sc);
    }

    public void Go(StorageWriter sc){

//        StorageWriter sc = new StorageWriter();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            DefaultHandler handle = new DefaultHandler() {
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

                    if(bstn){sc.setStn( new String(ch, start, length));bstn=false;}
                    if(bdate){sc.setDate( new String(ch, start, length));bdate=false;}
                    if(btime){sc.setTime( new String(ch, start, length));btime=false;}
                    if (btemp) {sc.setTemp(new String(ch, start, length));btemp = false;}
                    if (bdewp) {sc.setDewp(new String(ch, start, length));bdewp = false;}
                    if (bstp) {sc.setStp(new String(ch, start, length));bstp = false;}
                    if (bslp) {sc.setSlp(new String(ch, start, length));bslp = false;}
                    if (bvisib) {sc.setVisib(new String(ch, start, length));bvisib = false; }
                    if (bwdsp) { sc.setWdsp(new String(ch, start, length));bwdsp = false;}
                    if (bprcp) {sc.setPrcp(new String(ch, start, length));bprcp = false;}
                    if (bsndp) {sc.setSndp(new String(ch, start, length));bsndp = false;}
                    if (bfrshtt) {sc.setFrshht(new String(ch, start, length));bfrshtt = false;}
                    if (bcldc) { sc.setCldc(new String(ch, start, length));bcldc = false; }
                    if (bwnddir) {sc.setWnddir(new String(ch, start, length));bwnddir = false;}

                }
            };

            parser.parse("src/parser/output.xml", handle);
        } catch (Exception e) {
            System.out.println(e);
        }

        sc.process();

    }
}












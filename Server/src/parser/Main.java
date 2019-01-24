package parser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import storage.StorageConverter;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.io.StringReader;


public class Main {
    public static void main(String[] args) {
//    public Main() {
//
//    }
//
//    public void Parse(String xml){

        final StorageConverter sc = new StorageConverter();

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


                    if (btemp) {
                        String vtemp = new String(ch, start, length);
//                        System.out.println("Temperatuur: " + vtemp);
                        sc.setTemp(Float.parseFloat(vtemp));
                        btemp = false;
                    }
                    if (bdewp) {
                        String vdewp = new String(ch, start, length);
//                        System.out.println("Dauwpunt: " + vdewp);
                        sc.setDewp(Float.parseFloat(vdewp));
                        bdewp = false;
                    }
                    if (bstp) {
                        String vstp = new String(ch, start, length);
//                        System.out.println("Luchtdruk op stationsniveau: " + vstp);
                        sc.setStp(Float.parseFloat(vstp));
                        bstp = false;
                    }
                    if (bslp) {
                        String vslp = new String(ch, start, length);
//                        System.out.println("Luchtdruk op zeeniveau: " + vslp);
                        sc.setSlp(Float.parseFloat(vslp));
                        bslp = false;
                    }
                    if (bvisib) {
                        String vvisib = new String(ch, start, length);
//                        System.out.println("Zichtbaarheid: " + vvisib);
                        sc.setVisib(Float.parseFloat(vvisib));
                        bvisib = false;
                    }
                    if (bwdsp) {
                        String vwdsp = new String(ch, start, length);
//                        System.out.println("Windsnelheid: " + vwdsp);
                        sc.setWdsp(Float.parseFloat(vwdsp));
                        bwdsp = false;
                    }
                    if (bprcp) {
                        String vprcp = new String(ch, start, length);
//                        System.out.println("Neerslag: " + vprcp);
                        sc.setPrcp(Float.parseFloat(vprcp));
                        bprcp = false;
                    }
                    if (bsndp) {
                        String vsndp = new String(ch, start, length);
//                        System.out.println("Sneeuw: " + vsndp);
                        sc.setSndp(Float.parseFloat(vsndp));
                        bsndp = false;
                    }
                    if (bfrshtt) {
                        String a = new String(ch, start, length);
                        String vfrshht = "00"+a;
//                        if (a.charAt(0)=="1".charAt(0)) System.out.println("Het heeft gevroren");
//                        if (a.charAt(1)=="1".charAt(0)) System.out.println("Het heeft geregend");
//                        if (a.charAt(2)=="1".charAt(0)) System.out.println("Het heeft gesneeuwd");
//                        if (a.charAt(3)=="1".charAt(0)) System.out.println("Het heeft gehageld");
//                        if (a.charAt(4)=="1".charAt(0)) System.out.println("Er was onweer");
//                        if (a.charAt(5)=="1".charAt(0)) System.out.println("Er was een tornado of windhoos");
                        sc.setFrshht(Byte.parseByte(vfrshht,2));
                        bfrshtt = false;
                    }
                    if (bcldc) { sc.setCldc(new String(ch, start, length));bcldc = false; }
                    if (bwnddir) {
                        String vwnddir = new String(ch, start, length);
//                        System.out.println("Windrichting: " + vwnddir);
                        sc.setWnddir(Short.parseShort(vwnddir));
                        bwnddir = false;
                    }
                }
            };

            parser.parse("src/parser/output.xml", handle);
//            parser.parse(new InputSource(new StringReader(xml)), handle);
        } catch (Exception e) {
            System.out.println(e);
        }

        sc.process();

    }
}












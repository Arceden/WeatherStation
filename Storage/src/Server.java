import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class Server {

    public static void main(String[] args) {

        try {
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("./bin_output")
                    )
            );

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
                            bwinddir = false;

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
                        if (qName.equals("WINDDIR")) bwinddir = true;
                    }

                    public void characters(char[] ch, int start, int length) throws SAXException {
                        if (bstn) {
                            System.out.println("Station: " + new String(ch, start, length));
                            bstn = false;
                        }
                        if (bdate) {
                            System.out.println("Datum: " + new String(ch, start, length));
                            bdate = false;
                        }
                        if (btime) {
                            System.out.println("Tijd: " + new String(ch, start, length));
                            btime = false;
                        }
                        if (btemp) {
                            System.out.println("Temperatuur: " + new String(ch, start, length));

                            String test = new String(ch, start, length);
                            float f = Float.parseFloat(test);
                            System.out.println(f);

                            try {
                                out.writeFloat(f);
                                out.flush();
                            } catch (IOException e){
                                e.printStackTrace();
                            }

                            btemp = false;
                        }
                        if (bdewp) {
                            System.out.println("Dauwpunt: " + new String(ch, start, length));
                            bdewp = false;
                        }
                        if (bstp) {
                            System.out.println("Luchtdruk op stationsniveau: " + new String(ch, start, length));
                            bstp = false;
                        }
                        if (bslp) {
                            System.out.println("Luchtdruk op zeeniveau: " + new String(ch, start, length));
                            bslp = false;
                        }
                        if (bvisib) {
                            System.out.println("Zichtbaarheid: " + new String(ch, start, length));
                            bvisib = false;
                        }
                        if (bwdsp) {
                            System.out.println("Windsnelheid: " + new String(ch, start, length));
                            bwdsp = false;
                        }
                        if (bprcp) {
                            System.out.println("Neerslag: " + new String(ch, start, length));
                            bprcp = false;
                        }
                        if (bsndp) {
                            System.out.println("Sneeuw: " + new String(ch, start, length));
                            bsndp = false;
                        }
                        if (bfrshtt) {
                            String a = new String(ch, start, length);
                            if (a.charAt(0)=="1".charAt(0)) System.out.println("Het heeft gevroren");
                            if (a.charAt(1)=="1".charAt(0)) System.out.println("Het heeft geregend");
                            if (a.charAt(2)=="1".charAt(0)) System.out.println("Het heeft gesneeuwd");
                            if (a.charAt(3)=="1".charAt(0)) System.out.println("Het heeft gehageld");
                            if (a.charAt(4)=="1".charAt(0)) System.out.println("Er was onweer");
                            if (a.charAt(5)=="1".charAt(0)) System.out.println("Er was een tornado of windhoos");
                            bfrshtt = false;
                        }
                        if (bcldc) {
                            System.out.println("Bewolking: " + new String(ch, start, length));
                            bcldc = false;
                        }
                        if (bwinddir) {
                            System.out.println("Windrichting: " + new String(ch, start, length));
                            bwinddir = false;
                        }
                    }
                };

                parser.parse("src/output.xml", handle);
            } catch (Exception e) {
                System.out.println(e);
            }



        } catch (IOException e){
            e.printStackTrace();
        }


    }

}

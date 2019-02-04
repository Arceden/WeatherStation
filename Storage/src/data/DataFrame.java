package data;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class DataFrame {

    private static DataOutputStream dos;
    private static DataInputStream din;
    private final static String FILENAME = "data.wsd";

    /**
     * Save the Dataframe
     */
    public static void save_data(
        int stn,
        long timestamp,
        short temp,
        short dewp,
        short stp,
        short slp,
        short visib,
        short wdsp,
        short prcp,
        short sndp,
        byte frshht,
        short cldc,
        short wnddir
    ){

        try {
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                            //Weather Station Data Frame
                            new FileOutputStream("data/"+FILENAME, true)
                    )
            );

            try {

                //Store all data
                //Data has been compressed at the Raspberry pi, so it should not have to be recompressed here.
                out.writeInt(stn);
                out.writeLong(timestamp);
                out.writeShort(temp);
                out.writeShort(dewp);
                out.writeShort(stp);
                out.writeShort(slp);
                out.writeShort(visib);
                out.writeShort(wdsp);
                out.writeFloat(prcp);
                out.writeShort(sndp);
                out.writeByte(frshht);
                out.writeShort(cldc);
                out.writeShort(wnddir);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Open the WSD file
     */
    public static MappedByteBuffer loadData(){

        try {
            final FileChannel channel = new FileInputStream("data/"+FILENAME).getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            return buffer;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }

}

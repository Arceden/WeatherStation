package storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class StorageManager {

    //The data is currently stored in a single file
    private final String data_filename = "data/data_medium.wsd";
    private final byte frame_size = 55;

    private final byte stn_byte_pos =4;
    private final byte stn_size = 4;

    public StorageManager(){

    }

    public MappedByteBuffer loadData(){

        try {
            final FileChannel channel = new FileInputStream(this.data_filename).getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            return buffer;

        } catch (IOException e){
            e.printStackTrace();
        }

        return null;

    }

    public void readBuffer(MappedByteBuffer buffer){

        //As of writing this, the WeatherStationData frame is 55 bytes long for each record
        System.out.println("Amounts of rows: "+buffer.remaining()/frame_size);

//        getStn(buffer);
        getRecords(buffer, 8, 8);

    }

    /**
     * Retrieve all STN records from the Storage
     * @param buffer MappedByteBuffer
     */
    private void getStn(MappedByteBuffer buffer){

        int pos;
        for(int i=0;i<buffer.remaining()/frame_size;i++){

            pos=this.stn_byte_pos+(i*frame_size);

            byte[] stn_bytes = new byte[this.stn_size];
            for (int x=0;x<this.stn_size;x++){
                stn_bytes[x]=buffer.get(pos+x);
            }

            //Wrap the bytes so it can be read as anything other than a byte
            ByteBuffer wrapped = ByteBuffer.wrap(stn_bytes);
            System.out.println(wrapped.getInt());

        }

    }

    private void getRecords(MappedByteBuffer buffer, int offset, int length){

        int pos;
        for(int i=0;i<buffer.remaining()/frame_size;i++){
            pos=offset+(i*frame_size);
            byte[] ar = new byte[length];
            for(int x=0;x<length;x++){
                ar[x]=buffer.get(pos+x);
            }
            ByteBuffer wrapped = ByteBuffer.wrap(ar);
            //Example of using the wrapped buffer
//            Date d = new Date();
//            d.setTime(wrapped.getLong());
//            System.out.println(d.toString());

        }

    }

}

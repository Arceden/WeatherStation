package storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class StorageReader extends StorageManager {

    public enum DataFrame {
        STN, TIMESTAMP, TEMP, DEWP, STP, SLP, VISIB, WDSP, PRCP, SNDP, FRSHTT, CLDC, WNDDIR
    }

    public static void main(String[] args){
        new StorageReader();
    }

    public StorageReader(){
        query(loadData());
    }

    public MappedByteBuffer loadData(){

        try {
            final FileChannel channel = new FileInputStream(this.target_directory+this.target_filename).getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            return buffer;

        } catch (IOException e){
            e.printStackTrace();
        }

        return null;

    }

    //Request everything, just post it in console
    public void query(MappedByteBuffer buffer){



    }

}

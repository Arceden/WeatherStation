package server;
import data.StorageManager;
import data.StorageRecord;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Emitter {

    private final int PORT;
    private final InetAddress ADDRESS;

    private Socket socket;
    private DataOutputStream dos;
    private ExecutorService senderPool = Executors.newFixedThreadPool(1);

    public Emitter(InetAddress address, int port){
        this.PORT = port;
        this.ADDRESS = address;
    }

    public void connect() throws IOException {
        this.socket = new Socket(ADDRESS, PORT);
        this.dos = new DataOutputStream(this.socket.getOutputStream());
    }

    public void test() throws IOException {

        send(new StorageRecord());

    }

    /**
     * Send the dataframe to the Storage Server
     * @param record StorageRecord
     */
    public void send(StorageRecord record) {
        senderPool.submit(new Sender(record));
    }

    private class Sender implements Runnable {

        private StorageRecord record;

        public Sender(StorageRecord record){this.record=record;}

        @Override
        public void run(){

            try {
                dos.writeInt(7351674); //Send a small token to make sure this is seen as the start of the DataFrame
                dos.writeInt(record.getStn());
                dos.writeLong(record.getTimestamp());
                dos.writeShort((short) (record.getTemp() * 10));
                dos.writeShort((short) (record.getDewp() * 10));
                dos.writeShort((short) (record.getStp() * 10));
                dos.writeShort((short) (record.getSlp() * 10));
                dos.writeShort((short) (record.getVisib() * 10));
                dos.writeShort((short) (record.getWdsp() * 10));
                dos.writeFloat(record.getPrcp());
                dos.writeShort((short) (record.getSndp() * 10));
                dos.writeByte(record.getFrshht());
                dos.writeShort((short) (record.getCldc() * 10));
                dos.writeShort(record.getWnddir());
                dos.flush();
            } catch (IOException e){
                e.printStackTrace();
            }

        }

    }

}

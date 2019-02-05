package server;

import data.DataFrame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int port;
    private Thread serverThread;

    public Server(int port){
        this.port=port;
    }

    public void listen(){
        serverThread = new Thread(new Listener(this.port));
        serverThread.start();
    }

    private class Listener implements Runnable {

        private ServerSocket serverSocket;
        private ExecutorService socketPool = Executors.newFixedThreadPool(10);

        Listener(int port){
            try {
                this.serverSocket = new ServerSocket(port);
                System.err.println("Storage server listening on port "+port);
            } catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }
        }

        @Override
        public void run(){

            try {
                while(true){

                    //Wait and accept a new connection
                    final Socket connection = serverSocket.accept();
//                    DataHandler dataHandler = new DataHandler(connection);
//                    dataHandler.start();
                    socketPool.submit(new DataHandler(connection));

                }
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

        }

    }

    static class DataHandler extends Thread {
        private Socket connection;
        private DataInputStream dis;
        private boolean receiving;

        DataHandler(Socket connection) throws IOException {
            this.connection = connection;
            this.dis = new DataInputStream(connection.getInputStream());
            this.receiving = false;
        }

        @Override
        public void run(){
            while (true){

                try {

                    if(!receiving)
                        if(dis.readInt() == 7351674)
                            receiving=true;

                    if(receiving){

                        //The token has been accepted and all data should now be read
                        DataFrame.save_data(
                                dis.readInt(),
                                dis.readLong(),
                                dis.readShort(),
                                dis.readShort(),
                                dis.readShort(),
                                dis.readShort(),
                                dis.readShort(),
                                dis.readShort(),
                                dis.readFloat(),
                                dis.readShort(),
                                dis.readByte(),
                                dis.readShort(),
                                dis.readShort()
                        );
                        receiving = false;
                    }


                } catch (SocketException e){
                    try {
                        connection.close();
                    } catch (IOException se){
                        se.printStackTrace();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }

            }
        }

    }

}

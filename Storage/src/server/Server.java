package server;

import data.DataFrame;
import storage.StorageReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    private final int port;
    private Thread serverThread;
    private StorageReader storageReader;

    public Server(int port){
        this.port=port;
    }

    public void listen(){
        serverThread = new Thread(new Listener(this.port));
        serverThread.start();
    }

    public void setReader(StorageReader storageReader){
        this.storageReader = storageReader;
    }

    private class Listener implements Runnable {

        private ServerSocket serverSocket;

        Listener(int port){
            try {
                this.serverSocket = new ServerSocket(port);
                System.out.println("Storage server listening on port "+port);
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
                    DataHandler dataHandler = new DataHandler(connection);
                    dataHandler.start();

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
                                dis.readShort(),
                                dis.readShort(),
                                dis.readByte(),
                                dis.readShort(),
                                dis.readShort()
                        );
                        receiving = false;
                    }

                } catch (EOFException|SocketException e){
                    try {
                        connection.close();
                        break;
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

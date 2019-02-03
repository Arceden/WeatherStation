package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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

        Listener(int port){
            try {
                this.serverSocket = new ServerSocket(port);
                System.out.println("Storage listener initialized..");
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
        private BufferedReader inputStream;

        DataHandler(Socket connection) throws IOException {
            this.connection = connection;
            inputStream = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
        }

        @Override
        public void run(){
            while (true){

                try {
                    System.out.println(inputStream.readLine());
                } catch (IOException e){
                    e.printStackTrace();
                }


            }
        }

    }

}

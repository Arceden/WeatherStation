package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

                    //Wait for a new connection
                    Socket connection = serverSocket.accept();
                    System.err.println("Found a new connection! "+connection.getLocalAddress());

                    BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String s;
                    while ((s = bin.readLine()) != null) {
                        System.out.println(s);
                    }

                }
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

        }

    }

}

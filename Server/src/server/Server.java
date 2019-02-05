package server;

import data.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server {

    private final int PORT;
    private Parser parser;

    Server(int port){
        this.PORT = port;
    }

    void listen(){

        final ExecutorService stationProcessingPool = Executors.newFixedThreadPool(800);

        Runnable listenerTask = new Runnable() {
            @Override
            public void run() {

                try {
                    ServerSocket serverSocket = new ServerSocket(getPort());
                    System.out.println("Server listener initialized..");

                    while (true){
                        Socket connection = serverSocket.accept();
                        stationProcessingPool.submit(new DataProcessor(connection));
                    }
                } catch (IOException e){
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        };
        Thread listenThread = new Thread(listenerTask);
        listenThread.start();
    }

    void setParser(Parser parser) {
        this.parser = parser;
    }

    private int getPort() {
        return PORT;
    }

    private class DataProcessor implements Runnable {

        private Socket connection;

        private DataProcessor(Socket connection){
            this.connection=connection;
        }

        @Override
        public void run() {

            try {
                String s, xml = "";
                BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((s = bin.readLine()) != null) {
                    xml += s;

                    if (s.equalsIgnoreCase("\t</MEASUREMENT>")) {
                        xml = (xml.split("<MEASUREMENT>"))[1];
                        parser.Parse(xml);
                        xml = "";
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

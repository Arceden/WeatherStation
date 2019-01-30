package server;

import data.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

    private final int PORT;
    private Parser parser;

    Server(int port){
        this.PORT = port;
    }

    void listen(){

        Socket connection;
        String s;
        String xml;

        try {
            ServerSocket serverSocket = new ServerSocket(getPort());
            System.err.println("Server listener initialized..");

            while (true){

                connection = serverSocket.accept();

                System.err.println("Accepted a new connection from "+connection.getInetAddress()+" on port "+connection.getPort());
                BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));


                xml="";
                while ((s = bin.readLine()) != null){
                    /*
                     * Het data moet vanaf hier worden gestuurd naar de XML Parser
                     * Mogelijk op een andere thread zodat de inkomst van data nog door kan rammen
                     */

                    xml+=s;
                    if(s.equalsIgnoreCase("</WEATHERDATA>")){
                        parser.Parse(xml);
                        xml="";
                    }

                }

                connection.close();

            }

        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

    }

    void setParser(Parser parser) {
        this.parser = parser;
    }

    private int getPort() {
        return PORT;
    }
}

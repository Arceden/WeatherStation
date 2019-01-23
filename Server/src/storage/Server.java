package storage;

import parser.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 2500;

    public static void main(String args[]){

        Main par = new Main();

        Socket connection;
        String s;
        String xml;

        try {
            ServerSocket server = new ServerSocket(PORT);
            System.err.println("Server initialized..");

            while(true){
                connection = server.accept();

                System.err.println("Accepted a new connection from "+connection.getInetAddress()+" on port "+connection.getPort());
                BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                //Get the whole piece of XML on this variable
                xml = "";

                //Read through all the incoming lines
                while((s = bin.readLine()) != null){
                    xml+=s;
                    if(s.equalsIgnoreCase("</WEATHERDATA>")){
                        par.Parse(xml);
                        xml = "";
                    }
                }

                connection.close();

            }

        } catch (IOException e){}

    }

}
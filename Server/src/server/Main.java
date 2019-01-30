package server;

import data.Parser;
import data.StorageManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String args[]){

        final int port = 2500;

        //Initialize
        Server s = new Server(port);
        Parser p = new Parser();
        StorageManager sm = new StorageManager();

        //Settings
        s.setParser(p);             //Send the parser to the server
        s.listen();                 //Listen to the specified port

    }

}

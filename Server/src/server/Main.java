package server;

import data.Parser;
import data.StorageManager;

public class Main {

    public static void main(String args[]){

        final int port = 2500;

        Server s = new Server(port);
        Parser p = new Parser();
        StorageManager sm = new StorageManager();

        p.setStorageManager(sm);
        s.setParser(p);     //Send the parser to the server
        s.listen();         //Listen to the specified port

    }

}

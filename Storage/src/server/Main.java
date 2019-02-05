package server;

import server.Server;
import storage.StorageReader;

public class Main {

    public static void main(String args[]){

        //Initialise the server listener
        final int port = 7790;

        //Init classes
        Server s = new Server(port);
        StorageReader reader = new StorageReader();
        reader.keepUpToDate();

        //Execute
//        s.listen();

    }

}

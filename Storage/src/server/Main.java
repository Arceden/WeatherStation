package server;

import storage.StorageReader;

public class Main {

    public static void main(String args[]){

        //Initialise the server listener
        final int port = 7790;

        //Init classes
        Server s = new Server(port);
        WebSocket w = new WebSocket(port);
        StorageReader reader = new StorageReader();

//        reader.query("all");

        //Execute
//        w.listen();
        s.listen();

    }

}

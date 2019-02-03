package storage;

import server.Server;

public class Main {

    public static void main(String args[]){

        //Initialise the server listener
        final int port = 7790;

        //Init classes
        Server s = new Server(port);

        //Execute
        s.listen();

    }

}

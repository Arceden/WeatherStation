package storage;

import server.Server;

public class Main {

    public static void main(String args[]){

        //Initialise the server listener
        final int port = 7790;
        Server s = new Server(port);
        s.listen();

    }

}

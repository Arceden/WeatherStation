package server;

import config.Config;
import data.Emitter;
import data.Parser;
import data.StorageManager;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String args[]) throws Exception {

        //Get values from configuration file
        Config cfg = new Config();

        final int SERVER_PORT = Integer.parseInt(cfg.getProperty("server_port"));
        final int STORAGE_PORT = Integer.parseInt(cfg.getProperty("storage_port"));
        final InetAddress STORAGE_IP = InetAddress.getByName(cfg.getProperty("storage_ip"));

        //Initialize
        Server s = new Server(SERVER_PORT);
        Parser p = new Parser();

        //Setup the Emitter
        Emitter.init(STORAGE_IP, STORAGE_PORT);
        Emitter.connect();

        //Settings
        s.setParser(p);             //Send the parser to the server
        s.listen();                 //Listen to the specified port

    }

}

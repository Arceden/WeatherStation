package server;

import config.Config;
import data.Parser;
import data.StorageManager;

import java.net.InetAddress;

public class Main {

    public static void main(String args[]) throws Exception {

        //Get values from configuration file
        Config cfg = new Config();
        final int SERVER_PORT = Integer.parseInt(cfg.getProperty("server_port"));
        final int STORAGE_PORT = Integer.parseInt(cfg.getProperty("storage_port"));
        final InetAddress STORAGE_IP = InetAddress.getByName(cfg.getProperty("storage_ip"));

        //Initialize
        Emitter e = new Emitter(STORAGE_IP, STORAGE_PORT);
        Server s = new Server(SERVER_PORT);
        Parser p = new Parser();
        new StorageManager();

        //Execute commands
        StorageManager.setEmitter(e);
        s.setParser(p);             //Send the parser to the server
        e.connect();
        s.listen();                 //Listen to the specified port

    }

}
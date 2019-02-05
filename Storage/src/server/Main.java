package server;
import config.Config;
import storage.StorageReader;

public class Main {

    public static void main(String args[]){

        //Initialise the server listener
        Config cfg = new Config();
        final int port = Integer.parseInt(cfg.getProperty("storage_port"));
        final String export_directory = cfg.getProperty("export_directory");

        //Init classes
        Server s = new Server(port);
        StorageReader reader = new StorageReader();
        reader.setExportDirectory(export_directory);

        //Execute
        s.listen();
        reader.keepUpToDate();

    }

}
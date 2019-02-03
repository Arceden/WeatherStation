package server;

import data.StorageRecord;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Emitter {

    private static int PORT;
    private static Socket SOCKET;
    private static InetAddress ADDRESS;

    private static PrintWriter out;
    private static ObjectOutputStream outputStream;

    private static boolean yes=true;

    public static void init(InetAddress address, int port) {
        PORT = port;
        ADDRESS = address;
    }

    public static void connect() throws IOException {
        SOCKET = new Socket(ADDRESS, PORT);
        out = new PrintWriter(SOCKET.getOutputStream(), true);
        outputStream = new ObjectOutputStream(SOCKET.getOutputStream());
    }

    public static void test() throws IOException {

        while(true){

            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            PrintWriter out = new PrintWriter(SOCKET.getOutputStream(), true);
            out.println(input);
            out.flush();

        }

    }

    public static boolean send(StorageRecord record) {

        out.println(record.getStn());
        out.flush();

//        try {
//            outputStream.writeObject(record);
//            outputStream.flush();
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        return false;
    }

}

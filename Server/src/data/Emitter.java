package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Emitter {

    private static int PORT;
    private static InetAddress ADDRESS;
    private static Socket SOCKET;
    private static PrintWriter out;

    public static void init(InetAddress address, int port) {
        PORT = port;
        ADDRESS = address;
    }

    public static void connect() throws IOException {
        SOCKET = new Socket(ADDRESS, PORT);
        out = new PrintWriter(SOCKET.getOutputStream(), true);
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

    public static boolean send(StorageRecord record){



        return false;
    }

}

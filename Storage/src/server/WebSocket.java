package server;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSocket {

    private final int port;
    private Thread webThread;

    public WebSocket(int port){
        this.port = port;
    }

    public void listen(){
        webThread = new Thread(new Listener(this.port));
        webThread.start();
    }

    private class Listener implements Runnable {

        private ServerSocket webSocket;

        Listener(int port){

            try {
                this.webSocket = new ServerSocket(port);
                System.out.println("Storage server listening on port "+port);
            } catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }

        @Override
        public void run(){

            try {
                while (true){

                    //Wait and accept a new connection
                    final Socket socket = webSocket.accept();
                    SocketHandler socketHandler = new SocketHandler(socket);
                    socketHandler.start();

                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    class SocketHandler extends Thread {
        private Socket socket;
        private InputStream in;
        private BufferedWriter out;

        private boolean connected;

        SocketHandler(Socket socket) throws IOException {
            this.socket=socket;
            this.in = socket.getInputStream();
            this.out = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    socket.getOutputStream()
                            ) , "UTF-8"
                    )
            );
        }

        @Override
        public void run(){

            String data = new Scanner(in,"UTF-8").useDelimiter("\\r\\n\\r\\n").next();
            Matcher get = Pattern.compile("^GET").matcher(data);

            if(get.find()){

                try {
                    Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
                    match.find();
                    String HEADER_START = ("HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n"
                            + "Content-Length: ");
                    String HEADER_END = ("\r\n\r\n");

                    String CONTENT = "TEST";

                    out.write(HEADER_START+CONTENT.length()+HEADER_END+CONTENT);

                    this.connected = true;
                    System.out.println("YOOO");

                } catch (IOException e){
                    e.printStackTrace();
                }

            } else {
                try {
                    socket.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            while(true){

                try {
                    Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
                    match.find();
                    String HEADER_START = ("HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n"
                            + "Content-Length: ");
                    String HEADER_END = ("\r\n\r\n");

                    String CONTENT = "TEST";

                    out.write(HEADER_START+CONTENT.length()+HEADER_END+CONTENT);

                    this.connected = true;
                    System.out.println("YOOO");

                } catch (IOException e){
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                    connected=false;
                }
            }

        }

    }

}

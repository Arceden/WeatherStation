package server;

public class Server {
    public static void main(String[] args) {
        ThreadPooledServer server = new ThreadPooledServer(9000);
        new Thread(server).start();
        System.out.println("Server Started");

        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}

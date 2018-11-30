package tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {

    private ServerSocket serverSocket;

    public Server(int port) throws SocketException, IOException {
        serverSocket = new ServerSocket(port);
    }


    @Override
    public void run() {
        while (true) {
            Socket bridgeToClient = null;
            try {
                bridgeToClient = serverSocket.accept();
                SocketThread socketThread = new SocketThread(bridgeToClient);
                socketThread.start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server sv = new Server(8080);
        sv.run();
    }

}

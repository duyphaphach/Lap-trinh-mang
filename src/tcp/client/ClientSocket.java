package tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket {

    public static void main(String[] args) {
        String serverName = "localhost";
        int port = Integer.parseInt("8080");
        try {
            Socket client = new Socket(serverName, port);
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());

            // Write two int to server
            int a = 5;
            int b = 8;
            dataOutputStream.writeInt(a);
            dataOutputStream.writeInt(b);
            
            // Receive sum a and b from server
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readUTF());
            
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyph
 */
public class TCP extends Thread{
    private int port = 8080;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerSocket serverSocket;

    public TCP(int port) {
        this.port = port;
    }
    
    private int add (int a, int b) {
        return a + b;
    }
    
    private int sub (int a, int b) {
        return a - b;
    }
    
    private int mul (int a, int b) {
        return a * b;
    }
    
    private int div (int a, int b) {
        return a / b;
    }
    
    private int mod (int a, int b) {
        return a % b;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.serverSocket = new ServerSocket(this.port);

                Socket client = this.serverSocket.accept();

                System.out.println("Connecting to client at port: " + client.getRemoteSocketAddress());    
            
                this.in = new DataInputStream(client.getInputStream());
                this.out = new DataOutputStream(client.getOutputStream());
                
                int a = in.readInt();
                int b = in.readInt();
                
                String add = String.format("Tong hai so %d va %d la: %d", a, b, this.add(a, b));
                String sub = String.format("Hieu hai so %d va %d la: %d", a, b, this.sub(a, b));
                String mul = String.format("Tich hai so %d va %d la: %d", a, b, this.mul(a, b));
                String div = String.format("Thuong hai so %d va %d la: %d", a, b, this.div(a, b));
                String mod = String.format("Du %d chia %d la: %d", a, b, this.mod(a, b));

                out.writeUTF(add);
                out.writeUTF(sub);
                out.writeUTF(mul);
                out.writeUTF(div);
                out.writeUTF(mod);
                
                out.writeUTF("Thank kiu! Goodbye!");
                
                client.close();
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(TCP.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
            }
            
        }
    }
    
    public static void main(String[] args) {
        TCP a = new TCP(8080);
        a.start();
    }
}

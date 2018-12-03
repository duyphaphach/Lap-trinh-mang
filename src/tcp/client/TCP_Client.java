/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyph
 */
public class TCP_Client {

    public TCP_Client() {
        try {
            Socket s = new Socket("localhost", 8080);
            
            System.out.println("Connecting to server at " + s.getRemoteSocketAddress() + " port: 8080");
            
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            DataInputStream in = new DataInputStream(s.getInputStream());
            Random r = new Random(System.currentTimeMillis());
            
            int a = Math.abs(r.nextInt()%8000 + 2000);
            int b = Math.abs(r.nextInt()%1999);

            out.writeInt(a);
            out.writeInt(b);
            
            String add = in.readUTF();
            String sub = in.readUTF();
            String mul = in.readUTF();
            String div = in.readUTF();
            String mod = in.readUTF();
            
            System.out.println(add);
            System.out.println(sub);
            System.out.println(mul);
            System.out.println(div);
            System.out.println(mod);
            System.out.println(in.readUTF() + "\n");
            
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(TCP_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            TCP_Client t = new TCP_Client();
        }
    }
}

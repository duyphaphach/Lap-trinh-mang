/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyph
 */
public class SocketThread extends Thread {

    private Socket sc;

    public SocketThread(Socket s) {
        this.sc = s;
    }

    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public void run() {
        DataInputStream dataInputStream = null;
        try {
            // Init Stream to write data to client
            DataOutputStream dataOutputStream = new DataOutputStream(this.sc.getOutputStream());
            // Init Stream to accept data from client
            dataInputStream = new DataInputStream(this.sc.getInputStream());
            int a, b, s;
            a = dataInputStream.readInt();
            b = dataInputStream.readInt();
            s = add(a, b);
            dataOutputStream.writeUTF(String.format("%d + %d = %d", a, b, s));
            Thread.sleep(5000);
            dataOutputStream.writeUTF(String.format("End please!"));
            this.sc.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

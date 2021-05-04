/*
  Created by Linfeng Li on 4/23/2021
  University of Illinois at Chicago
 */
package main;

import java.net.ServerSocket;
import java.net.Socket;

/*
    threaded class to listen to client connection in a separate thread
 */
public class Listener implements Runnable{

    String address;
    int port;
    ServerSocket serverSocket;
    Socket socket;
    Listener(String address, int port, Socket socket) {
        this.port = port;
        this.address = address;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            this.socket = serverSocket.accept();
            System.out.println("AS 2 connected @ " + address + " " + port);
            AuthServer.setSocket(this.socket);
        }catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

}

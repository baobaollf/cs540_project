package main;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    String IP;
    ServerSocket serverSocket;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    Server(String IP, int port) {
        this.IP = IP;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started waiting for client");
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            socket = serverSocket.accept();
            System.out.println("Client connected!");
        }
        catch (Exception e){
            System.out.println("ERROR:" + e);
        }
    }

}

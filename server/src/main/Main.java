package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    ServerSocket serverSocket;
    Socket socket;

    ObjectInputStream in;
    ObjectOutputStream out;
    SecurityObject object;
    private int id = 0;

    public static void main(String[] args) {

        Main main = new Main();
        System.out.println("hello from main");
        System.out.println("initiating auth servers and populate its content servers");
        AuthServer authServer1 = new AuthServer("127.0.0.1", 8081);
        AuthServer authServer2 = new AuthServer("127.0.0.1", 8082);


        List<ContentServer> contentServerList1 = new ArrayList<>();
        ContentServer contentServer1 = new ContentServer("0.0.0.2", 8080);
        contentServerList1.add(contentServer1);
        contentServer1.content = "content server 1";
        contentServerList1.add(contentServer1);



        // assign content servers for each auth server
        authServer1.setContentServers(contentServerList1);

        System.out.println("connect auth servers");
        authServer1.addNearByAuthServer(authServer2);


        authServer1.setUsers("username", "password");

        authServer1.run();
        

    }


}

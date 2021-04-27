/*
  Created by Linfeng Li on 4/23/2021
  University of Illinois at Chicago
 */
package main;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/*
    driver class to server side second server service.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("hello from auth server 2");
        System.out.println("initiating auth servers and populate its content servers");
        AuthServer authServer2 = new AuthServer("127.0.0.1", 8083);

        List<ContentServer> contentServerList2 = new ArrayList<>();
        ContentServer contentServer2 = new ContentServer("0.0.0.3", 8080);
        contentServer2.content = "content server 2";
        contentServerList2.add(contentServer2);

        // assign content servers for each auth server
        authServer2.setContentServers(contentServerList2);
        AuthServer authServer1 = new AuthServer("127.0.0.1", 8081);
        authServer2.addNearByAuthServer(authServer1);
        authServer2.run();

        

    }


}

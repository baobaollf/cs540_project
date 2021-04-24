package main;

import java.io.*;
import java.net.Socket;

public class Client {
    private String username;
    private String password;
    String address;
    int port;
    private Socket socketClient;
    SecurityObject object;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    boolean isLogin;

    Client(String address, int port) {
        this.address = address;
        this.port = port;
        isLogin = false;
        try {

            this.socketClient = new Socket(address, port);
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }


    public void login() throws IOException {
        while(!isLogin) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter username ");
            username = reader.readLine();
            System.out.print("Enter password ");
            password = reader.readLine();
            this.object = new SecurityObject(username, password, address);
            this.object.body.setBody("LOGIN");
            sendObject();
            receiveObject();
            if (object.body.getBody().equals("LOGIN_SUCCESS")) {
                System.out.println("login success");
                isLogin = true;
            } else {
                System.out.println("login fail");
            }
        }
    }

    public void getContent(){
        object.body.setBody("GET_CONTENT");
        sendObject();
        receiveObject();
        System.out.println(object.body.getBody());
    }

    public void switchAS() {
        object.body.setBody("SWITCH_AS");
        sendObject();
        this.port = this.object.availableAuthServers.get(0);
        try {
//            this.socketClient.close();
            this.socketClient = new Socket(address, port);
            getContent();
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
        sendObject();
    }

    public void logout() {
        object.body.setBody("LOGOUT");
        this.isLogin = false;
        sendObject();
    }

    public void sendObject() {
        try {
            this.out = new ObjectOutputStream(this.socketClient.getOutputStream());
            this.out.writeObject(this.object);
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

    public void receiveObject() {
        try {
            this.in = new ObjectInputStream(this.socketClient.getInputStream());
            this.object = (SecurityObject) this.in.readObject();
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

}

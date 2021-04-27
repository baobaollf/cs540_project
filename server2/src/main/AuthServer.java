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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuthServer extends Server {
    private final int CLIENT_PORT = 8082;
    private final String SOCKET_ADDRESS = "127.0.0.1";
    //       security key   object
    private Map<String, SecurityObject> loggedInList;
    private List<ContentServer> contentServers;
    private Map<String, String> users;
    private List<AuthServer> nearByAuthServers;
    SecurityObject object;
    ServerSocket serverSocket;
    static Socket socket;

    Socket authClientSocket;
    Socket authSocket;
    ObjectInputStream authIn;
    ObjectOutputStream authOut;

    ObjectInputStream in;
    ObjectOutputStream out;

    AuthServer(String IP, int port) {
        super(IP, port);
        users = new HashMap<>();
        loggedInList = new HashMap<>();
        nearByAuthServers = new ArrayList<>();

    }
    /**
     * auth server driver function
     */
    public void run() {
        try {
            // connect to AS1
            this.authClientSocket = new Socket(IP, port);

            // listen for client connection
            Listener listener = new Listener(SOCKET_ADDRESS, CLIENT_PORT, socket);
            Thread thread = new Thread(listener);
            thread.start();


            while (socket == null) {
                receiveObject();
            }

            while (true) {
                receiveObjectFromClient();
                String commend = object.body.getBody();

                if (commend.equals("LOGOUT")) {
                    logout(object.username, object.password);
                    System.out.println(object.clientIP + " is logged out");
                    break;
                }
                if ("GET_CONTENT".equals(commend)) {
                    object = getContent(object);
                    sendObjectToClient();
                }
            }


        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void addNearByAuthServer(AuthServer authServer) {
        this.nearByAuthServers.add(authServer);
    }

    public void setContentServers(List<ContentServer> contentServers) {
        this.contentServers = contentServers;
    }

    public void setUsers(String username, String password) {
        this.users.put(username, password);
    }


    /**
     * helper function to determine if user has timed out
     * if the response is in the hashmap, meaning the user have logged in before;
     * update the timestamp if it hasn't expire.
     *
     * @param object client input
     * @return `True` if the user is in authorizedObjects and timestamp hasn't expire
     */
    private boolean validate(SecurityObject object) {
        if (loggedInList.containsKey(object.getHashKey())) {
            if (loggedInList.get(object.getHashKey()).getTime().timeStamp >= System.currentTimeMillis()) {
                loggedInList.get(object.getHashKey()).updateTime();
                object.updateTime();
                return true;
            }
        }
        return false;
    }


    /**
     * helper function to check if the username and password is valid
     *
     * @param username client input
     * @param password client input
     * @return `True` if username and password matches database.
     */
    private boolean credentialInDatabase(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    /**
     * if `request` is true, meaning client wants content
     * - validate the response
     *
     * @param object input information from client
     * @return requested contents
     */
    public SecurityObject getContent(SecurityObject object) {
        if (validate(object)) {
            object.body.setBody(contentServers.get(0).fetchContent(object.contentSerialID));
            object.contentSerialID++;
            return object;
        }
        object.body.setBody("login expired");
        return object;
    }

    /**
     * if the username and password matches database
     * we create a security object and put it in hashmap and notify nearby
     * CDN clusters through backbone
     *
     * @param object Security Object
     * @return login status
     */
    public boolean login(SecurityObject object) {
        if (credentialInDatabase(object.username, object.password)) {
            loggedInList.put(object.getHashKey(), object);

            return true;
        }
        return false;
    }

    /**
     * with valid username and password, remove the user from logged in list
     *
     * @param username client identifier
     * @param password client password
     */
    public void logout(String username, String password) {
        if (credentialInDatabase(username, password)) {
            loggedInList.remove(username);
            System.out.println(username + " is logged out and removed Security Object");
        }
    }
    /**
     * send security object as output stream to connected auth server
     */
    public void sendObject() {
        try {
            this.authOut = new ObjectOutputStream(this.authClientSocket.getOutputStream());
            this.authOut.writeObject(object);
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }


    /**
     * read from connected auth server through input stream
     * and update security object
     */
    public void receiveObject() {
        try {
            this.authIn = new ObjectInputStream(this.authClientSocket.getInputStream());
            object = (SecurityObject) this.authIn.readObject();
            loggedInList.put(object.getHashKey(), object);
        } catch (Exception e) {
            System.out.println("Disconnected from AS 1");
            try {
                authClientSocket.close();
            } catch (Exception e2) {
                System.out.println("ERROR:" + e2);
            }
        }
    }

    /**
     * send local security object to output stream
     * and send to connected client
     */
    public void sendObjectToClient() {
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.out.writeObject(object);
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

    /**
     * read from input stream from connected client
     * and update local security object
     */
    public void receiveObjectFromClient() {
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            object = (SecurityObject) this.in.readObject();
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

    public static void setSocket(Socket socket) {
        AuthServer.socket = socket;
    }


}
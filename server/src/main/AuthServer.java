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
    private final int AUTH_SERVER_PORT = 8083;
    //       security key   object

    private List<ContentServer> contentServers;
    private Map<String, String> users;private Map<String, SecurityObject> loggedInList;
    private List<AuthServer> nearByAuthServers;
    SecurityObject object;
    ServerSocket serverSocket;
    Socket socket;

    ServerSocket authServerSocket;
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
            // connect to AS2
            authServerSocket = new ServerSocket(AUTH_SERVER_PORT);
            authSocket = authServerSocket.accept();
            System.out.println("Near by auth server connected!");


            serverSocket = new ServerSocket(port);
            System.out.println("Server started waiting for client to connect");
            socket = serverSocket.accept();
            System.out.println("Client connected!");


        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }

        // receive content request
        while (true) {
            receiveObject();
            String commend = object.body.getBody();

            if (commend.equals("LOGOUT")) {
                logout(object.username, object.password);
                System.out.println(object.clientIP + " is logged out");
                break;
            }
            if (commend.equals("SWITCH_AS")) {
                try {
                    socket.close();
                    System.out.println("closing client socket");
                    authSocket.close();
                    System.out.println("closing auth server socket");
                } catch (Exception e) {
                    System.out.println("ERROR:" + e);
                }
                System.out.println("auth server switched");
                break;
            }
            switch (commend) {
                case "LOGIN": {
                    boolean loginStatus = login(object);
                    if (loginStatus) {
                        object.serverIP = IP;
                        System.out.println("client connected to auth server at: " + object.serverIP);
                        object.body.setBody("LOGIN_SUCCESS");

                        object.availableAuthServers.add(nearByAuthServers.get(0).port);

                    } else {
                        System.out.println("username or password incorrect");
                        object.serverIP = getIP();
                        object.body.setBody("LOGIN_FAIL");
                    }
                    sendObject();
                    break;
                }
                case "GET_CONTENT": {
                    object = getContent(object);
                    sendObject();
                    break;
                }
            }
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
            System.out.println(object.body.getBody());
            object.contentSerialID++;
            sendObjectToAS();
            // notify nearby clusters through backbone that user is active
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
     * @param object SecurityObject
     * @return login status
     */
    public boolean login(SecurityObject object) {
        if (credentialInDatabase(object.username, object.password)) {
            loggedInList.put(object.getHashKey(), object);
            // notify nearby clusters through backbone
            sendObjectToAS();
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
            // notify nearby clusters through backbone
            System.out.println(username + " is logged out and Security Object removed");
            sendObjectToAS();
        }
    }

    /**
     * send security object as output stream to client
     */
    public void sendObject() {
        try {
            this.out = new ObjectOutputStream(this.socket.getOutputStream());
            this.out.writeObject(object);
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

    /**
     * read from client through input stream
     * and update security object
     */
    public void receiveObject() {
        try {
            this.in = new ObjectInputStream(this.socket.getInputStream());
            object = (SecurityObject) this.in.readObject();
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

    /**
     * send local security object to output stream
     * and send to connected auth server
     */
    public void sendObjectToAS() {
        try {
            this.authOut = new ObjectOutputStream(this.authSocket.getOutputStream());
            this.authOut.writeObject(object);
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

    /**
     * read from input stream from connected auth server
     * and update local security object
     */
    public void receiveObjectFromAS() {
        try {
            this.in = new ObjectInputStream(this.socket.getInputStream());
            object = (SecurityObject) this.in.readObject();
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }


}
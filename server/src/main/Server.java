package main;


public class Server {

    String IP;
    int port;

    Server(String IP, int port) {
        this.IP = IP;
        this.port = port;
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return port;
    }
}

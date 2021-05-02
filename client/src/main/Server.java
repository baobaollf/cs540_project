/*
  Created by Linfeng Li on 4/23/2021
  University of Illinois at Chicago
 */
package main;
/*
    Parent class to different kind of servers
 */
public class Server {

    String IP;
    int port;

    public Server(String IP, int port) {
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

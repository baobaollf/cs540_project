package test;

import static org.junit.Assert.*;
import main.*;
import org.junit.Test;

/*
 * Created by Linfeng Li on 04/27/21
 * University of Illinois Chicago
 */
public class ServerTest {
    Server server;


    /**
     *Function to test get ip address from server
     * */
    @Test
    public void getIP() {
        server = new Server("192.0.0.1", 5000);
        String ip = "192.0.0.1";
        assertEquals(ip, server.getIP());
    }
    /**
     *Function to test get port from server
     * */
    @Test
    public void getPort() {
        server = new Server("192.0.0.1", 5000);
        int port = 5000;
        assertEquals(port, server.getPort());
    }

}
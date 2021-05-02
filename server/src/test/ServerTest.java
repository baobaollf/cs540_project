package test;

import main.Server;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {
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
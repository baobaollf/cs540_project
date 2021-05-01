package test;
import main.*;

import org.junit.Before;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/*
 * Created by Linfeng Li on 04/27/21
 * University of Illinois Chicago
 */
public class AuthServerTest {
    AuthServer server1;
    AuthServer server2;
    ContentServer contentServer;
    SecurityObject securityObject;
    String username, password, address;

    @Before
    public void init(){
        try {
            server1 = new AuthServer("127.0.0.1",2444);
            server2 = new AuthServer("127.0.0.1", 2555);
            contentServer = new ContentServer("127.0.0.1", 5000);
            username = "username";
            password = "password";
            address = "127.0.0.1";
            securityObject = new SecurityObject(username,password,password);
            server1.setUsers(username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
    *Function to test if near authorization server is added to AS member list
    * */
    @Test
    public void addNearByAuthServer() {
        server1.addNearByAuthServer(server2);
        List<AuthServer> l1 = server1.getNearByAuthServers();
        assertEquals(l1.get(0), server2);

    }

    /**
     *Function to test if content server is added to AS member list
     * */
    @Test
    public void setContentServers() {
        List<ContentServer> l1 = new ArrayList<>();
        l1.add(contentServer);
        server1.setContentServers(l1);
        assertEquals(l1.get(0), contentServer);
    }

    /**
     *Function to test if user is added to AS member list
     * */
    @Test
    public void setUsers() {
        assertTrue(server1.getUsers().containsKey("username"));
    }

    /**
     *Function to test get content from AS
     * */
    @Test
    public void getContent() {
        SecurityObject sec = server1.getContent(securityObject);
        assertEquals(securityObject,sec);

    }

    /**
     *Function to test successful login
     * */
    @Test
    public void login() {
        assertTrue(server1.login(securityObject));
    }

    /**
     *Function to test successful logout and removal of security object
     * */
    @Test
    public void logout() {
        server1.logout("username", "password");
        assertTrue(server1.credentialInDatabase("username", "password"));
    }



}
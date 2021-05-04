package test;

import main.SecurityObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Created by Linfeng Li on 04/27/21
 * University of Illinois Chicago
 */
public class SecurityObjectTest {
    SecurityObject securityObject;
    @Before
    public void init(){
        String username = "Joe";
        String password = "password";
        String address = "127.0.0.1";
        securityObject = new SecurityObject(username, password, address);

    }

    @Test
    public void getUsername() {
        assertEquals(securityObject.getUsername(), "Joe");
    }

    @Test
    public void setUsername() {
        //  Changing the security objects name and checking if it updates
        securityObject.setUsername("Li");
        assertEquals(securityObject.getUsername(), "Li");
    }

    @Test
    public void getPassword() {
        assertEquals(securityObject.getPassword(), "password");
    }

    @Test
    public void setPassword() {
        securityObject.setPassword("password1");
        assertEquals(securityObject.getPassword(), "password1");
    }

    @Test
    public void updateTime() {
        long timestamp = securityObject.getTime().getTimeStamp();
        securityObject.updateTime();
        assertTrue(timestamp <= securityObject.getTime().getTimeStamp());
    }

    @Test
    public void testToString() {

        assertEquals(securityObject.toString(),

                "SecurityObject{" +
                        "hashKey='" + securityObject.getHashKey().toString() + '\'' +
                        ", time=" + securityObject.getTime().toString() +
                        ", clientIP='" + "127.0.0.1" + '\'' +
                        ", username='" + "Joe" + '\'' +
                        ", password='" + "password" + '\'' +
                        '}');
    }
}
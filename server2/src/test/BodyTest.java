package test;

import main.Body;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Created by Linfeng Li on 05/02/21
 * University of Illinois Chicago
 */
public class BodyTest {

    Body body;
    @Before
    public void init(){
        body = new Body();
        body.setBody("cs540");
    }

    @Test
    public void getBody(){
        assertEquals("cs540", body.getBody());
    }


    @Test
    public void setBody() {
        body.setBody("test");
        assertEquals("test", body.getBody());
    }

}
package test;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import org.junit.Assert.*;
import main.Body;

import static org.junit.Assert.assertEquals;

/*
 * Created by Linfeng Li on 04/27/21
 * University of Illinois Chicago
 */
public class BodyTest {

    @Before
    public void init(){
        Body body = new Body();
        body.setBody("cs540");
    }

    @Test
    public void getBody(){
//        assertEquals();
    }


    @Test
    public void setBody() {

//        assertEquals("cs540", body.getBody());
    }
}
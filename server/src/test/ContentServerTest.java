package test;

import main.Body;
import main.ContentServer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Created by Linfeng Li on 04/27/21
 * University of Illinois Chicago
 */
public class ContentServerTest {

    ContentServer server;

    @Before
    public void init(){
        server = new ContentServer("127.0.0.1", 8080);
    }

    @Test
    public void fetchContent() {
        assertEquals("serving content from  with identifier 0", server.fetchContent(0));
    }
}
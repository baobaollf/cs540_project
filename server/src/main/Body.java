package main;

import java.io.Serializable;

public class Body implements Serializable {
    String body = "";

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

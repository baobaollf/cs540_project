/*
  Created by Linfeng Li on 4/23/2021
  University of Illinois at Chicago
 */
package main;

import java.io.Serializable;

/*
    object used to stored user request/server response type
 */
public class Body implements Serializable {
    String body = "";

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

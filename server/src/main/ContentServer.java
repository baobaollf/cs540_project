/*
  Created by Linfeng Li on 4/23/2021
  University of Illinois at Chicago
 */
package main;


/*
    extends server object
 */
public class ContentServer extends Server {

    String content;

    ContentServer(String IP, int port) {
        super(IP, port);
    }

    /**
     * output corresponding contents
     * @param id content id used to identified contents
     * @return content corresponds to input id
     */
    public String fetchContent(int id) {
        return content + id;
    }
}

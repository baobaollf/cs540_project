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

    public ContentServer(String IP, int port) {
        super(IP, port);
        this.content = "";
    }

    /**
     * output corresponding contents
     * @param id content id used to identified contents
     * @return content corresponds to input id
     */
    public String fetchContent(int id) {
        return "serving content from " + content + " with identifier " + id;
    }
}

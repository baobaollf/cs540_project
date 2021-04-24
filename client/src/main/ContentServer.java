package main;

public class ContentServer extends Server {

    String content;

    ContentServer(String IP, int port) {
        super(IP, port);
    }

    public String fetchContent(int id) {
        return content + id;
    }
}
